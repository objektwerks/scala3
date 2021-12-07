Scala 3
-------
>Scala 3 feature tests.

Test
----
1. sbt clean test

Run
---
1. sbt "run Fred Flintstone"

Model
-----
1. Client --- Command ---> Dispatcher
2. Dispatcher --- T... ---> Service
3. Service --- T... ---> Store | Service(s) ( optional )
4. Service --- Either[Throwable, T] ---> Dispatcher
5. Dispatcher --- Event ---> Client

Docs
----
1. Book - https://docs.scala-lang.org/scala3/book/introduction.html
2. Reference - https://docs.scala-lang.org/scala3/reference/overview.html