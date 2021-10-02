# 05. Criando um projeto web

Para criar um projeto web com maven, o processo é muito similar ao anterior.

```
$ mvn archetype:generate -DartifactId=webstore -DgroupId=io.dev.app.webstore \
-DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp
```

A seguinte estrutura de arquivos será criada:

```
webstore/
├── pom.xml
├── src
│   └── main
│       ├── resources
│       └── webapp
│           ├── index.jsp
│           └── WEB-INF
│               └── web.xml
└── target
    └── classes
```

As mesmas opções podem ser utilizadas para criar o projeto utilizando o Eclipse.

```
Create project >> Maven >> Maven projet ...
```

## Adicionando o servidor

Agora para fazermos o deploy da aplicação em desenvolvimento, adicionamos o plugin do servidor, nesse caso, o Jetty.

```
    <plugin>
      <groupId>org.eclipse.jetty</groupId>
      <artifactId>jetty-maven-plugin</artifactId>
      <version>9.3.7.v20160115</version>
    </plugin>
```

Inicialmente, utilizaremos a configuração do Jetty na sua forma mais simples.

Para subir o servidor, execute o goal:

```
$ mvn jetty:run
```

Se tudo correr bem, seu bash ficará bloqueado com a seguinte mensagem:

```
[INFO] Started ServerConnector@95416d{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
[INFO] Started @31950ms
[INFO] Started Jetty Server
```

Para finalizar o serviço, execute `CTRL+C`.

```
[INFO] Stopped ServerConnector@95416d{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
...
[INFO] Jetty server exiting.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
```

## Realizando algumas correções no projeto

- **Adicionando o plugin da API Servlets**: apesar do Jetty resolver essa dependência internamente, é importante adicionarmos explicitamente o Servlet no xml para evitarmos erros no ambiente/IDE.

```
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```

- **Atualizando o web.xml**: Dentro da pasta WEB-INF, o xml existente está se referindo a uma versão antigo de Servet, atualizaremos para a versão 3.1. Substituimos todos os antigo xml:

```
<!DOCTYPE web-app PUBLIC
"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
"http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>
</web-app>
```

pelo novos modelo:

```
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
    http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
    version="3.1">
</web-app>
```

> TODO: Reagrupar os sources folders no Eclipse.

## Criando um Servlet

Por padrão, o projeto já é criado com pelo menos um arquivo jsp na raiz do diretório webapp. Esse arquivo, apesar de ter extensão jsp, quando carregador no servidor, é convertido para um servet.

Mas vamos criar outro servlet, esse com um arquivo .java.

```
package io.dev.servlets;

// ...imports 

@WebServlet(urlPatterns={"/mensagem"}) // definindo o contexto
public class MensagemServlet extends HttpServlet { // extendendo HttpServlet
    @Override // sobrescrevendo o métod doGet
    protected void doGet(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter(); // recuperando o writer da resposta
        out.println("<h1 style='color: red;'>Hello Servlets</h1>");
        out.close();
    }    
}
```

Reiniciando o servidor e acessando o endereço `localhost:8080/mensagem` podemos ver a saída html.

## Alterando o contexto

Adicionando mais uma configuração no `pom.xml`, podemos alterar o contexto do nosso Servlet.

```
  <configuration>
    <scanIntervalSeconds>10</scanIntervalSeconds>
        <webApp>
            <contextPath>/store</contextPath>
        </webApp>
    </configuration>
```

Com isso, a url mudou para `http://localhost:8080/store/mensagem`.
