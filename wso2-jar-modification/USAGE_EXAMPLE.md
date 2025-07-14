# Quick Usage Example

## Step-by-Step Guide to Modify WSO2 API Manager Jar

### 1. Place Your WSO2 Jar File
First, copy your `org.wso2.carbon.apimgt.gateway_5.0.3.jar` file to this directory or note its path.

### 2. Run the Main Script
```bash
./complete_build.sh /path/to/your/org.wso2.carbon.apimgt.gateway_5.0.3.jar
```

**Example output:**
```
[STEP] Checking Java version...
openjdk version "1.8.0_452"
OpenJDK Runtime Environment (build 1.8.0_452-8u452-ga~us1-0ubuntu1~25.04-b09)
OpenJDK 64-Bit Server VM (build 25.452-b09, mixed mode)

[STEP] Creating working directories...
[STEP] Extracting jar file...
[INFO] Found APIAuthenticationHandler.class
[STEP] Creating modified Java source file...
[INFO] Modified Java source file created
[STEP] Creating compilation script...
[STEP] Creating decompiler script...
[INFO] Build scripts created successfully!
```

### 3. (Optional) Decompile Original Class
To understand the original class structure:
```bash
./build/decompile_original.sh
```

This creates `decompiled/APIAuthenticationHandler_javap.txt` with class information.

### 4. Review and Edit Modified Source
The modified Java source is created at:
```
build/src/org/wso2/carbon/apimgt/gateway/handlers/security/APIAuthenticationHandler.java
```

It already contains your requested `handleRequest` method modification. You may need to add other methods from the original class.

### 5. Compile and Package
```bash
./build/compile_and_package.sh
```

**Success output:**
```
Starting compilation process...
Setting up classpath...
Compiling with classpath: :/path/to/jars...
Compilation successful!
Copying compiled class to jar structure...
Repackaging jar file...
Modified jar created: org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar
```

### 6. Use the Modified Jar
Your modified jar is now available at:
```
build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar
```

## Expected Directory Structure After Completion

```
wso2-jar-modification/
├── complete_build.sh
├── modify_wso2_jar.sh
├── README.md
├── USAGE_EXAMPLE.md
├── temp/                          # Extracted original jar
│   ├── org/
│   │   └── wso2/
│   │       └── carbon/
│   │           └── apimgt/
│   │               └── gateway/
│   │                   └── handlers/
│   │                       └── security/
│   │                           └── APIAuthenticationHandler.class
│   └── [other jar contents]
├── build/
│   ├── src/
│   │   └── org/
│   │       └── wso2/
│   │           └── carbon/
│   │               └── apimgt/
│   │                   └── gateway/
│   │                       └── handlers/
│   │                           └── security/
│   │                               └── APIAuthenticationHandler.java
│   ├── compile_and_package.sh
│   ├── decompile_original.sh
│   └── org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar
└── decompiled/
    └── APIAuthenticationHandler_javap.txt
```

## Modified Method

The `handleRequest` method in your modified jar will contain:

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

## Troubleshooting Tips

### If Compilation Fails:
1. Check the `decompiled/APIAuthenticationHandler_javap.txt` file
2. Add missing methods to your modified Java source
3. Ensure all imports are correct
4. Verify dependencies are available

### If the Original Class Has More Methods:
You may need to add them to your modified source file. Use the decompiler output to identify required methods.

### Testing the Modified Jar:
1. Backup your original jar: `cp original.jar original.jar.backup`
2. Replace with modified jar: `cp build/org.wso2.carbon.apimgt.gateway_5.0.3_modified.jar /path/to/wso2/lib/`
3. Restart WSO2 API Manager
4. Check logs for the enhanced error messages

The modified jar will provide enhanced logging for troubleshooting API authentication issues in WSO2 API Manager.