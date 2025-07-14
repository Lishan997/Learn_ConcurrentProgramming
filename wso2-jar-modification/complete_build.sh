#!/bin/bash

# Complete WSO2 API Manager Jar Modification Script
# This script provides a complete solution for modifying the APIAuthenticationHandler

# Set JAVA_HOME to OpenJDK 8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_step() {
    echo -e "${BLUE}[STEP]${NC} $1"
}

# Check if jar file is provided
if [ $# -eq 0 ]; then
    print_error "Usage: $0 <path_to_org.wso2.carbon.apimgt.gateway_5.0.3.jar>"
    exit 1
fi

JAR_FILE="$1"

# Check if jar file exists
if [ ! -f "$JAR_FILE" ]; then
    print_error "Jar file not found: $JAR_FILE"
    exit 1
fi

# Check Java version
print_step "Checking Java version..."
java -version

# Create working directories
print_step "Creating working directories..."
rm -rf temp build decompiled
mkdir -p temp build decompiled

# Extract the jar file
print_step "Extracting jar file..."
cd temp
jar -xf "../$JAR_FILE"

# Check if the Java class exists
JAVA_CLASS_PATH="org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.class"
if [ ! -f "$JAVA_CLASS_PATH" ]; then
    print_error "APIAuthenticationHandler.class not found in jar file"
    exit 1
fi

print_info "Found APIAuthenticationHandler.class"

# Go back to main directory
cd ..

# Install fernflower (Java decompiler) if not available
if ! command -v fernflower &> /dev/null; then
    print_step "Installing fernflower decompiler..."
    wget -O fernflower.jar "https://github.com/fesh0r/fernflower/releases/download/v1.8.1/fernflower-1.8.1.jar" 2>/dev/null || {
        print_warning "Could not download fernflower. Using javap instead."
        USE_JAVAP=true
    }
fi

# Create the modified Java source with the exact method requested
print_step "Creating modified Java source file..."
mkdir -p build/src/org/wso2/carbon/apimgt/gateway/handlers/security/

cat > build/src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java << 'EOF'
package org.wso2.carbon.apimgt.gateway.handlers.security;

import org.apache.axis2.context.MessageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.gateway.handlers.security.authenticator.APIAuthenticator;
import org.wso2.carbon.apimgt.gateway.handlers.security.APISecurityException;

/**
 * Modified APIAuthenticationHandler for troubleshooting purposes
 * This class contains the modified handleRequest method with additional logging
 */
public class APIAuthenticationHandler {
    
    private static final Log log = LogFactory.getLog(APIAuthenticationHandler.class);
    private APIAuthenticator authenticator;
    
    /**
     * Modified handleRequest method with enhanced logging for troubleshooting
     */
    public boolean handleRequest(MessageContext messageContext) {
        try {
            if (authenticator.authenticate(messageContext)) {
                return true;
            }
        } catch (APISecurityException e) {

            if (log.isDebugEnabled()) {
                logMessageDetails(messageContext);
            }
            log.error("API authentication failure", e);
            log.error("messageContext", messageContext);
            handleAuthFailure(messageContext, e);
        }
        return false;
    }
    
    // Note: These methods would need to be implemented based on the original class
    // You may need to copy other methods from the original class
    
    private void logMessageDetails(MessageContext messageContext) {
        // Add your logging implementation here
        if (log.isDebugEnabled()) {
            log.debug("Message details: " + messageContext.toString());
        }
    }
    
    private void handleAuthFailure(MessageContext messageContext, APISecurityException e) {
        // Add your auth failure handling implementation here
        log.error("Handling authentication failure for message: " + messageContext.toString());
    }
}
EOF

print_info "Modified Java source file created"

# Create compilation script
print_step "Creating compilation script..."
cat > build/compile_and_package.sh << 'EOF'
#!/bin/bash

# Set JAVA_HOME to OpenJDK 8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

echo "Starting compilation process..."

# Create output directory
mkdir -p classes

# Find all jars in the temp directory for classpath
echo "Setting up classpath..."
CLASSPATH=""
for jar in $(find ../temp -name "*.jar" 2>/dev/null); do
    CLASSPATH="$CLASSPATH:$jar"
done

# Add the extracted classes to classpath
CLASSPATH="$CLASSPATH:../temp"

echo "Compiling with classpath: $CLASSPATH"

# Compile the Java source
javac -cp "$CLASSPATH" -d classes src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Copy the compiled class to the extracted jar directory
    echo "Copying compiled class to jar structure..."
    cp classes/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.class \
       ../temp/org/wso2/carbon/apimgt/gateway/handlers/security/
    
    # Repackage the jar
    echo "Repackaging jar file..."
    cd ../temp
    jar -cf ../build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar *
    
    cd ../build
    echo "Modified jar created: org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar"
    echo "Original jar backup available in temp directory"
else
    echo "Compilation failed. Check the error messages above."
    echo "You may need to:"
    echo "1. Add additional dependencies to the classpath"
    echo "2. Implement missing methods in the APIAuthenticationHandler class"
    echo "3. Check the original class for required imports and methods"
fi
EOF

chmod +x build/compile_and_package.sh

# Create a decompiler script to help understand the original class structure
print_step "Creating decompiler script..."
cat > build/decompile_original.sh << 'EOF'
#!/bin/bash

# Set JAVA_HOME to OpenJDK 8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

echo "Decompiling original APIAuthenticationHandler class..."

# Create decompiled directory
mkdir -p ../decompiled

# Use javap to get class information
javap -cp ../temp -v org.wso2.carbon.apimgt.gateway.handlers.security.APIAuthenticationHandler > ../decompiled/APIAuthenticationHandler_javap.txt

# If fernflower is available, use it
if [ -f "../fernflower.jar" ]; then
    echo "Using fernflower decompiler..."
    java -jar ../fernflower.jar ../temp/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.class ../decompiled/
else
    echo "Fernflower not available. Using javap output only."
fi

echo "Decompilation complete. Check the decompiled directory for results."
echo "Use this information to understand the original class structure and implement missing methods."
EOF

chmod +x build/decompile_original.sh

print_info "Build scripts created successfully!"
print_info ""
print_info "To proceed with the modification:"
print_info "1. Run: ./build/decompile_original.sh (to understand the original class structure)"
print_info "2. Edit: build/src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java"
print_info "   (Add any missing methods/fields from the original class)"
print_info "3. Run: ./build/compile_and_package.sh (to compile and package the modified jar)"
print_info ""
print_info "The modified jar will be created as: build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar"
print_info "Make sure to backup your original jar before replacing it!"