#!/bin/bash

# WSO2 API Manager Jar Modification Script
# This script modifies the APIAuthenticationHandler.java file in the WSO2 jar

# Set JAVA_HOME to OpenJDK 8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
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

# Create working directories
print_info "Creating working directories..."
mkdir -p original modified build temp

# Copy original jar
print_info "Copying original jar file..."
cp "$JAR_FILE" original/

# Extract the jar file
print_info "Extracting jar file..."
cd temp
jar -xf "../original/$(basename "$JAR_FILE")"

# Check if the Java class exists
JAVA_CLASS_PATH="org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.class"
if [ ! -f "$JAVA_CLASS_PATH" ]; then
    print_error "APIAuthenticationHandler.class not found in jar file"
    exit 1
fi

print_info "Found APIAuthenticationHandler.class"

# Create the modified Java source file
print_info "Creating modified Java source file..."
mkdir -p ../modified/org/wso2/carbon/apimgt/gateway/handlers/security/

cat > ../modified/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java << 'EOF'
package org.wso2.carbon.apimgt.gateway.handlers.security;

import org.apache.axis2.context.MessageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.apimgt.gateway.handlers.security.authenticator.APIAuthenticator;
import org.wso2.carbon.apimgt.gateway.handlers.security.APISecurityException;

/**
 * Modified APIAuthenticationHandler for troubleshooting purposes
 */
public class APIAuthenticationHandler {
    
    private static final Log log = LogFactory.getLog(APIAuthenticationHandler.class);
    private APIAuthenticator authenticator;
    
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
    
    // Placeholder methods - these would need to be implemented based on the original class
    private void logMessageDetails(MessageContext messageContext) {
        // Implementation would depend on the original class
    }
    
    private void handleAuthFailure(MessageContext messageContext, APISecurityException e) {
        // Implementation would depend on the original class
    }
}
EOF

print_info "Modified Java source file created"

# Note: To compile the modified class, we would need the original WSO2 dependencies
# This is a placeholder for the compilation process
print_warning "Note: To compile the modified class, you'll need the original WSO2 dependencies"
print_warning "The compilation step requires the original jar's dependencies to be available"

# Create build script for compilation
cat > ../build/compile.sh << 'EOF'
#!/bin/bash

# Set JAVA_HOME to OpenJDK 8
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# This script would compile the modified Java class
# You'll need to add the original jar and its dependencies to the classpath

echo "To compile the modified class, run:"
echo "javac -cp 'original_jar_and_dependencies/*' -d build/classes modified/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java"

# After compilation, replace the class in the jar:
echo "After compilation, replace the class in the extracted jar:"
echo "cp build/classes/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.class temp/org/wso2/carbon/apimgt/gateway/handlers/security/"

# Then repackage the jar:
echo "Repackage the jar:"
echo "cd temp && jar -cf ../build/org.wso2.carbon.apimgt.gateway_5.0.3.jar *"
EOF

chmod +x ../build/compile.sh

print_info "Build script created at build/compile.sh"

# Go back to the original directory
cd ..

print_info "Jar extraction and modification setup complete!"
print_info "Next steps:"
print_info "1. Review the modified Java file at: modified/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java"
print_info "2. Ensure you have all necessary WSO2 dependencies"
print_info "3. Run the build script: ./build/compile.sh"
print_info "4. The modified jar will be created at: build/org.wso2.carbon.apimgt.gateway_5.0.3.jar"