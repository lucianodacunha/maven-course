# 02. Maven: Eclipse

## Importando um projeto no Eclipse

- `File` > `Import` > `Maven` > `Existing Maven Projects` > Selecione o diretório > `Finish`

Na importação, serão criados alguns arquivos necessário para a IDE, como o `.classpath`, `.settings` e o `.project`. 

Existe a opção de criar esses arquivo utilizando o próprio Maven:

```
mvn eclipse:eclipse
```

## Adicionando uma lib
    - Acesso o [mvn repository](http://mvnrepository.com/)
    - Copie o xml de sua lib
    - Adicione no arquivo pom.xml

Exemplo: 

```xml
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
```

### Alternativa para gerenciamento de dependências: [ivy](https://ant.apache.org/ivy/)
