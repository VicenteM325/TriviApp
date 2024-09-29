<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>WebFormBuilder - ${user.nombre}</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <!-- Barra de navegacion -->
        <jsp:include page="/WEB-INF/user/navBar.jsp"/>

        <div class="container-fluid">
            <div class="row mt-5">
                <div class="col-1"></div>
                <div class="col-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>Formularios creados</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Tiempo</th>
                                        <th>Tema</th>
                                        <th>Fecha creacion</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="form" items="${formularios}" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${form.id}</td>
                                            <td>${form.usuario}</td>
                                            <td>${form.nombre}</td>
                                            <td>${form.tema}</td>
                                            <td>${form.fechaCreacion}</td>
                                            <td>
                                                <button type="button" class="btn btn-outline-info" onclick="copyTC('http://localhost:8080/${pageContext.request.contextPath}/builder?id=${form.id}');">Copiar link</button>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/export?id=${form.id}" class="btn btn-secondary">
                                                    <i class="fas fa-file-export"></i> Exportar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <script>
            function copyTC(value) {
                const t = document.createElement('textarea');
                t.value = value;
                t.style.visibility = true;
                document.body.appendChild(t);
                t.select();
                document.execCommand('copy');
                t.parentElement.removeChild(t);
            }
        </script>
    </body>
</html>
