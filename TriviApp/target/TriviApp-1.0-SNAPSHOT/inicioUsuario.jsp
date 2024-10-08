<%@page import="com.triviapp.modelo.Formulario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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
                                        <td><%= i + 1%></td>
                                        <td><%= form.getId()%></td>
                                        <td><%= form.getNombre()%></td>
                                        <td><%= form.getTiempo()%></td>
                                        <td><%= form.getTema()%></td>
                                        <td><%= form.getFechaCreacion()%></td>
                                        <td>
                                            <button type="button" class="btn btn-outline-info" onclick="copyTC('http://localhost:8080/<%= request.getContextPath()%>/builder?id=<%= form.getId()%>');">Copiar link</button>
                                            <a href="<%= request.getContextPath()%>/export?id=<%= form.getId()%>" class="btn btn-secondary">
                                                <i class="fas fa-file-export"></i> Exportar
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="7" class="text-center">No hay Trivias Creadas.</td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>

                            <!-- TextArea para introducir solicitudes -->
                            <div class="mt-4">
                                <h5>Texto Solicitud</h5>
                                <textarea id="requestText" rows="5" class="form-control" placeholder="Introduce tu solicitud aquí..."></textarea>
                                <input type="hidden" id="loggedUser" value="${user.nombre}">
                            </div>

                            <!-- Input para cargar archivo .xson -->
                            <div class="mt-3">
                                <input type="file" id="fileInput" class="form-control" accept=".xson">
                                <button class="btn btn-secondary mt-2" onclick="loadFileContent();">Cargar Archivo</button>
                            </div>

                            <div class="mt-3">
                                <button class="btn btn-primary" onclick="sendToRequestReader();">Enviar Solicitud</button>
                                <button class="btn btn-success" onclick="sendToImportForm();">Importar Trivias</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JS -->
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <script>
            // Función para copiar el link
            function copyTC(value) {
                const tempInput = document.createElement('input');
                tempInput.value = value;
                document.body.appendChild(tempInput);
                tempInput.select();
                document.execCommand('copy');
                document.body.removeChild(tempInput);
            }

            // Función para cargar archivo .xson y mostrar su contenido en el textarea
            function loadFileContent() {
                const fileInput = document.getElementById('fileInput');
                const textarea = document.getElementById('requestText');

                if (fileInput.files.length > 0) {
                    const file = fileInput.files[0];
                    const reader = new FileReader();

                    reader.onload = function(e) {
                        textarea.value = e.target.result;
                    };

                    reader.onerror = function() {
                        alert('Error al leer el archivo.');
                    };

                    // Leemos el archivo como texto
                    reader.readAsText(file);
                } else {
                    alert('Por favor selecciona un archivo primero.');
                }
            }

            // Función para enviar solicitud al servidor
            function sendToRequestReader() {
                const text = document.getElementById('requestText').value;
                const loggedUser = document.getElementById('loggedUser').value;
                if (text) {
                    fetch("${pageContext.request.contextPath}/requestReader", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'text/plain',
                            'loggedUser': loggedUser
                        },
                        body: text
                    })
                            .then(response => response.text())
                            .then(data => {
                                alert(data);
                            })
                            .catch(error => {
                                console.error('Error:', error);
                                alert("Error al enviar a RequestReader.");
                            });
                } else {
                    alert("El texto es obligatorio.");
                }
            }

            // Función para enviar importación de trivia
            function sendToImportForm() {
                const text = document.getElementById('requestText').value;
                const loggedUser = document.getElementById('loggedUser').value;
                const idForm = prompt("Ingrese el ID del formulario:");
                if (text && idForm) {
                    fetch("${pageContext.request.contextPath}/import", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'text/plain',
                            'loggedUser': loggedUser,
                            'idForm': idForm
                        },
                        body: text
                    })
                            .then(response => response.text())
                            .then(data => {
                                alert("Respuesta de ImportForm: " + data);
                            })
                            .catch(error => {
                                console.error('Error:', error);
                                alert("Error al enviar a ImportForm.");
                            });
                } else {
                    alert("El texto y el ID del formulario son obligatorios.");
                }
            }
        </script>
    </body>
</html>
