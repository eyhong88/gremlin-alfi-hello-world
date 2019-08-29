# alfi-hello-world
Source Code for Gremlin's Tutorial on "Getting Started with Application Level Failure Injection (ALFI) - Hello World" 

Check out the tutorial [here]( 
https://www.gremlin.com/community/tutorials/getting-started-with-application-level-failure-injection-alfi-hello-world/)

Configure an ALFI attack via [Gremlin UI](https://app.gremlin.com/alfi)<br>

Setup Traffic Coordinates in code.


Create a simple endpoint for testing the Client.
<br><br>
<b>
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello, World!";
    }
}
</b>
