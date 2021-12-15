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
2. Dispatcher --- license ---> Authorizor
3. Dispatcher --- T... ---> Validator
4. Dispatcher --- T... ---> Service
5. Service --- T... ---> Store --- Email ---> Emailer ( via Store.register )
6. Service --- Either[Throwable, T] ---> Dispatcher
7. Dispatcher --- Event ---> Client

Docs
----
1. Book - https://docs.scala-lang.org/scala3/book/introduction.html
2. Reference - https://docs.scala-lang.org/scala3/reference/overview.html