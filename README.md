# 01. Maven: Intro

Ferramenta de automação de compilação.

## Instalação

- [Download](https://maven.apache.org/download.cgi)
- Instalação: 
```
$ tar -xvf apache-maven-...
# mv apache-maven-... /opt/maven3
```
- Adicione ao final do `~/.bashrc`
```
export MVN_HOME="/opt/maven3/"
export PATH="$PATH:${MVN_HOME}/bin"
```

##  Primeiro Projeto

- Forma mais simples de executar o maven:

```
mvn archetype:generate
```

Será necessário responder algumas perguntas referentes à configuração do projeto. Basicamente, será necessário definir os seguintes parâmetros:

- **artifactId**: nome do projeto.
- **groupId**: geralmente a url invertida, como é utilizada no package.
- **interactiveMode**: false/true, caso false, nada mais será perguntado.
- **archetypeArtifactId**: o archetype do projeto.
- **archetypeVersion**: a versão do archetype. Utilizando essa, evitará gerar o projeto em uma versão muito antiga do maven.

Esses parâmetros, podem ser fornecidos no momento da geração do archetype.

```
mvn archetype:generate  -DartifactId=example -DgroupId=com.company \
-DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart \
-DarchetypeVersion=1.4
```
A seguinte estrutura é gerada inicialmente:

```
example/
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── company
    │               └── App.java
    └── test
        └── java
            └── com
                └── company
                    └── AppTest.java
```

## Maven Phases

- **compile**: Compila os arquivos, gerando os .class e depositando-os em uma pasta `target`. 

Agora temos a seguinte estrutura de diretórios:

```
../example/
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── company
│   │               └── App.java
│   └── test
│       └── java
│           └── com
│               └── company
│                   └── AppTest.java
└── target
    └── classes
        └── com
            └── company
                └── App.class
```
- **test**: executa os tests existentes, gerando um relatório em `txt` no diretório `target/surefire-reports`. 

```
Test set: com.company.AppTest
-------------------------------------------------------------------------------
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.031 s - in com.company.AppTest
```

- **package**: empacota o arquivo compilado para distribuição, conforme definido previamento no `pom.xml`.

## Executando o projeto

- `.class`: Para executar um projeto que possui somente o .class, utilize o comando:

```
mvn exec:java -Dexec.mainClass="com.company.App"

```

- `.jar`: Para executar um projeto existente em um jar, utilize o comando:

```
$ java -cp app.jar com.company.App
```

Porém, caso exista alguma dependência, o jar gerado por padrão terá somente os arquivos do projeto. Para criar um .jar que inclua as dependências, também conhecido como `fat jar`, é necessário adicionar um plugin.

Exemplo de configuração do plugin.

```
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.3.0</version>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
                <archive>
                    <manifest>
                        <mainClass>com.company.App</mainClass>
                    </manifest>
                </archive>                
            </configuration>
            <executions>
                <execution>
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>  
```

Com o plugin configurado, execute:

```
$ mvn clean package
$ java -cp example-1.0-SNAPSHOT-jar-with-dependencies.jar com.company.App
```

### Mais em [Maven Guide](https://maven.apache.org/guides/getting-started/index.html)

