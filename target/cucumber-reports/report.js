$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/demoqa/Home.feature");
formatter.feature({
  "name": "HOME FEATURE",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Navigate to home page",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@AUTO-001"
    },
    {
      "name": "@home"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I Open \"Home\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "HomeSteps.iOpenPage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click to button by contains text \"ELEMENT\"",
  "keyword": "And "
});
formatter.match({
  "location": "HomeSteps.ClickToModule(String)"
});
formatter.result({
  "error_message": "java.lang.Error: Expected condition failed: waiting for core.Keyword$$Lambda$377/0x00000008003c2040@ab4aa5e (tried for 45 second(s) with 500 milliseconds interval)\n\tat core.Keyword.waitForLoadPage(Keyword.java:557)\n\tat steps.demoqa.HomeSteps.ClickToModule(HomeSteps.java:32)\n\tat ✽.I click to button by contains text \"ELEMENT\"(file:src/test/java/features/demoqa/Home.feature:6)\n",
  "status": "failed"
});
formatter.after({
  "status": "passed"
});
});