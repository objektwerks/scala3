Scala 3
-------
>Scala3 feature tests.

Test
----
1. sbt clean test

Run
---
1. sbt "run Fred Flintstone"

Model
-----
1. Client --- Command ---> Dispatcher --- T ---> Service
2. Service --- Future[Either[Throwable, T]] ---> Dispatcher
3. Dispatcher --- Event ---> Client

Docs
----
1. Book - https://docs.scala-lang.org/scala3/book/introduction.html
2. Reference - https://docs.scala-lang.org/scala3/reference/overview.html