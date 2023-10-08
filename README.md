# USER GUIDE TESTING

## Maven Run


```sh
-ea -Dcucumber.options="src/test/java/features --tags'@AUTO-181'" -Dmode="CHROME" -Denvironment="demo" -Dapplication="DEMO"
```

| Param | Description |
| ------ |-----------|
| Tag | Tag to run Feature - Scenario |
| Mode | [CHROME],[FIREFOX],[IE],[SAFARI],[EDGE_LEGAXY],[EDGE_CHROMIUM],[H_CHROME],[H_FIREFOX],[COC_COC],[OPERA],[EDGE],[CHEADLESS],[IOS],[ANDROID],[API] |
| Environment | [staging]\|[beta]\|[demo] |



## Run With Jenkins

[//]: # ()
[//]: # (|  |  |)

[//]: # (| ------ | ------ |)

[//]: # (| Link | http://35.213.134.118:8080/|)

[//]: # (| User | admin/CloudHMS@2022 |)

[//]: # (| Job | VinHMSAutomation |)

#### Build

Branch
```sh
master (default) or any branch
```