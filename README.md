# 03. Repositórios: Remoto/Local

Todos os plugins baixados são mantidos em cópia e versão em um diretório local (cache).

Caso seja especificado uma nova versão no `pom.xml`, um novo download será realizado e acrescentado no cache.

```
$ tree -L 1 ~/.m2/repository/
~/.m2/repository/
├── commons-lang
├── commons-logging
├── junit
└── log4j
```
Por padrão, o maven sempre fará uma consulta remota por novas versões. Por exemplo, ao adicionar uma nova dependência:

```
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

 e compilar novamente

 ```
 $ mvn compile
 ```

Novos downloads serão realizados

```
Downloading from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.pom (6.0 kB at 4.8 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson-parent/2.8.8/gson-parent-2.8.8.pom
Downloaded from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson-parent/2.8.8/gson-parent-2.8.8.pom (4.6 kB at 17 kB/s)
Downloading from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.jar
Downloaded from central: https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.8.8/gson-2.8.8.jar (242 kB at 412 kB/s)

```
## Utilizar somente o cache

Para utilizar somente fontes do cache, utilize o parâmentro `-o` (Work offline) ao executar o maven.

```
mvn -o compile
```

Caso tenha informado alguma dependência que ainda não exista no cache, o build irá falhar:

```
[INFO] BUILD FAILURE
...
[ERROR] ... Cannot access central (https://repo.maven.apache.org/maven2) in offline mode ...
```