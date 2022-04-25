# Talks

**Speaker Description (Philip Riecks - rieckpil)**: Philip is an independent software consultant with over seven years of professional experience. Apart from his freelance work, he's running a blog and a YouTube channel to educate Java developers about #SpringBoot, #AWS, #Testing under the slogan _Testing Java Applications Made Simple_. Philip is convinced that testing can be joyful once you know the _how_. Besides that, he's one of the authors of _Stratospheric - From Zero to Production with Spring Boot and AWS_.

Links:
- [Blog](https://rieckpil.de)
- [Twitter](https://twitter.com/rieckpil)
- Speaker Image Reference: [400x400](/resources/rieckpil-400x400.jpg) or [200x257](/resources/rieckpil-200x257.jpg)

## How Fixing a Broken Window Cut Down Our Build Time by 50%

<p align="center">
  <a href="https://rieckpil.de">
    <img src="/resources/broken-window.png" alt="Broken Window"/>
  </a>
</p>

**Talk description**: The broken windows (not the operating system - 😁) theory not only applies to criminological cases but also codebases. It only takes one lazy developer to break the first window. Chances are high that others will follow a similar path and take workarounds or re-apply a not so optimal pattern thinking, "It passed the code review, I must not be that bad".

For our Spring Boot integration test setup, we had such a broken window that was causing ever-growing build times. While most books about clean architecture emphasize composition over inheritance, we drifted off from this rule and coupled our tests to an AbstractIntegrationTest class that we then annotated with `@DirtiesContext`...

This resulted in ever-growing build times as each new integration test was launching its own Spring TestContext, adding valuable seconds to the overall build time. Our feedback cycles, as well as the overall satisfaction of the team, started to suffer from this.

This talk describes our journey of getting rid of `@DirtiesContext` on top of an abstract test class (aka. the "broken window") that made reusing the Spring TestContext impossible and resulted in ever-growing build times. Furthermore, we'll discuss recipes, best practices, and antipatterns for resuing the Spring TestContext and testing Spring Boot applications in general.

After fixing this "broken window", our overall build time went down by 50% from 25 to 12 minutes.

- [Source Code](/fixing-a-broken-window/)
- Slides