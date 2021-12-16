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
2. Dispatcher --- Command ---> Authorizer
3. Dispatcher --- Command ---> Validator
4. Validator --- Command ---> Handler
5. Handler --- T ---> Service
6. Service --- T ---> Store --- Email ---> Emailer ( via Store.register )
7. Service --- Either[Throwable, T] ---> Handler
8. Handler --- Event ---> Dispatcher
9. Dispatcher --- Event ---> Client

Docs
----
1. Book - https://docs.scala-lang.org/scala3/book/introduction.html
2. Reference - https://docs.scala-lang.org/scala3/reference/overview.html