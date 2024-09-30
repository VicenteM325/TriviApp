<%@page import="java.util.List"%>
<%@page import="com.triviapp.modelo.Formulario"%>
<%@page import="com.triviapp.modelo.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TriviApp - ${user.nombre}</title>

    <!-- CSS -->
    <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
</head>
<body>

    <!-- Barra de navegación -->
    <jsp:include page="/WEB-INF/user/navBar.jsp"/>

    <div class="container-fluid">
        <div class="row mt-5">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Trivias creadas</h4>
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
                                    <th>Fecha creación</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Formulario> formularios = (List<Formulario>) request.getAttribute("formularios");
                                    if (formularios != null && !formularios.isEmpty()) {
                                        for (int i = 0; i < formularios.size(); i++) {
                                            Formulario form = formularios.get(i);
                                %>
                                <tr>
                                    <td><%= i + 1 %></td>
                                    <td><%= form.getId() %></td>
                                    <td><%= form.getNombre() %></td>
                                    <td><%= form.getTiempo_pregunta() %></td>
                                    <td><%= form.getTema() %></td>
                                    <td><%= form.getFechaCreacion() %></td>
                                    <td>
                                        <button type="button" class="btn btn-outline-info" onclick="copyTC('http://localhost:8080/<%= request.getContextPath() %>/builder?id=<%= form.getId() %>');">Copiar link</button>
                                        <a href="<%= request.getContextPath() %>/export?id=<%= form.getId() %>" class="btn btn-secondary">
                                            <i class="fas fa-file-export"></i> Exportar
                                        </a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="7" class="text-center">No hay trivias creadas.</td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>

                        <!-- Formulario para enviar solicitud -->
                        <h5>Enviar Solicitud al Servidor</h5>
                        <form id="requestForm">
                            <div class="form-group">
                                <label for="loggedUser">Usuario:</label>
                                <input type="text" id="loggedUser" class="form-control" value="${user.nombre}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="requestData">Datos de Solicitud:</label>
                                <textarea id="requestData" class="form-control" rows="5" placeholder="Escribe tu solicitud aquí..." required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Enviar Solicitud</button>
                        </form>

                        <div id="response" class="mt-3"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- JS -->
    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

    <script>
        document.getElementById('requestForm').addEventListener('submit', function (event) {
            event.preventDefault(); 

            const loggedUser = document.getElementById('loggedUser').value.trim();
            const requestData = document.getElementById('requestData').value.trim();

            fetch('<%= request.getContextPath() %>/requestReader', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'loggedUser': loggedUser 
                },
                body: JSON.stringify({ data: requestData }) 
            })
            .then(response => response.text())
            .then(data => {
                document.getElementById('response').innerText = data; 
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('response').innerText = 'Error al enviar la solicitud.';
            });
        });
    </script>
</body>
</html>

