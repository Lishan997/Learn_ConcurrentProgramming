# WSO2 API Manager Jar Modification Tool

This tool helps you modify the `APIAuthenticationHandler` class in the WSO2 API Manager jar file (`org.wso2.carbon.apimgt.gateway_5.0.3.jar`) for troubleshooting purposes.

## Overview

The tool modifies the `handleRequest` method in `APIAuthenticationHandler.java` to add enhanced logging for troubleshooting API authentication issues, as requested:

```java
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
```

## Prerequisites

- OpenJDK 8 (already installed and configured)
- WSO2 API Manager jar file: `org.wso2.carbon.apimgt.gateway_5.0.3.jar`
- Linux environment with bash shell

## Directory Structure

```
wso2-jar-modification/
├── complete_build.sh          # Main script to set up modification environment
├── modify_wso2_jar.sh         # Alternative script for basic setup
├── README.md                  # This file
└── [Generated directories after running scripts]
    ├── temp/                  # Extracted jar contents
    ├── build/                 # Build scripts and output
    │   ├── src/              # Modified Java source
    │   ├── compile_and_package.sh
    │   └── decompile_original.sh
    └── decompiled/           # Decompiled original class info
```

## Usage Instructions

### Step 1: Setup and Extract

Run the main script with your WSO2 jar file:

```bash
./complete_build.sh /path/to/org.wso2.carbon.apimgt.gateway_5.0.3.jar
```

This will:
- Extract the jar file
- Verify the APIAuthenticationHandler class exists
- Create the modified Java source with your requested changes
- Generate build scripts

### Step 2: Understand the Original Class (Optional but Recommended)

To understand the original class structure:

```bash
./build/decompile_original.sh
```

This will:
- Use `javap` to analyze the original class
- Download and use fernflower decompiler if possible
- Generate information about the original class structure

### Step 3: Customize the Modified Class

Edit the generated Java source file:

```bash
nano build/src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java
```

The file already contains your requested `handleRequest` method modification. You may need to:
- Add any missing methods from the original class
- Add missing imports
- Implement placeholder methods based on the original class

### Step 4: Compile and Package

Run the compilation script:

```bash
./build/compile_and_package.sh
```

This will:
- Compile the modified Java class using OpenJDK 8
- Replace the original class in the extracted jar
- Create a new jar file: `build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar`

## Key Features

### 1. Enhanced Logging
The modified `handleRequest` method includes:
- Debug logging for message details
- Error logging for authentication failures
- Error logging for message context

### 2. OpenJDK 8 Compatibility
- All scripts are configured to use OpenJDK 8 (Java 1.8)
- Compilation uses the correct Java version as requested

### 3. Automated Process
- Extracts original jar
- Creates modified source
- Handles compilation and repackaging
- Maintains original jar backup

## Troubleshooting

### Compilation Errors

If compilation fails:

1. **Missing Dependencies**: The script automatically includes jars from the extracted directory in the classpath. If you have additional WSO2 dependencies, add them to the classpath in `build/compile_and_package.sh`.

2. **Missing Methods**: Use the decompiler output to understand what methods need to be implemented in the modified class.

3. **Import Issues**: Check the original class for required imports and add them to your modified source.

### Example Fixes

If you encounter missing methods, you can add them based on the original class:

```java
// Add methods based on original class structure
private void someMethod() {
    // Implementation from original class
}
```

## Important Notes

### Backup Original Jar
Always backup your original jar file before replacing it:

```bash
cp org.wso2.carbon.apimgt.gateway_5.0.3.jar org.wso2.carbon.apimgt.gateway_5.0.3.jar.backup
```

### Testing
Test the modified jar in a development environment before using in production.

### WSO2 Version Compatibility
This tool is specifically designed for WSO2 API Manager version 5.0.3. For other versions, you may need to adjust the class path and method signatures.

## File Locations

After successful execution:
- **Modified jar**: `build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar`
- **Modified source**: `build/src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java`
- **Original jar backup**: Available in the `temp/` directory
- **Decompiled info**: `decompiled/APIAuthenticationHandler_javap.txt`

## Environment Information

- **Java Version**: OpenJDK 8 (1.8.0_452)
- **Java Home**: `/usr/lib/jvm/java-8-openjdk-amd64`
- **Compilation Target**: Java 8 bytecode

## Support

If you encounter issues:
1. Check the decompiled class information
2. Verify all dependencies are available
3. Ensure the original jar contains the expected class
4. Review compilation error messages for missing imports or methods

The modified jar file will contain your requested logging enhancements for troubleshooting WSO2 API Manager authentication issues.