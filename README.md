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
1. Client --- Command ---> SyncDispatcher
2. SyncDispatcher --- T... ---> SyncService
3. SyncService --- T... ---> MapStore ( via register: Emailer --- Email ---> Smtp )
4. SyncService --- Either[Throwable, T] ---> SyncDispatcher
5. SyncDispatcher --- Event ---> Client

Docs
----
1. Book - https://docs.scala-lang.org/scala3/book/introduction.html
2. Reference - https://docs.scala-lang.org/scala3/reference/overview.html