<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.lang.Float"%>
<jsp:include page="header.jsp" />
    <main class="container py-5">
      <div id="alerts" name="alerts">
        <jsp:include page="alerts.jsp" />
      </div>
      <div class="card">
        <div class="card-header bg-dark text-light">
          <div class="row">
            <div class="col-sm-2">
              <a class="btn btn-outline-light" href="/GestionEtablissement/accueil"> < </a>
            </div>
            <div class="col-sm-8">
              <h5 class="card-title text-center">Graphiques</h5>
            </div>
            <div class="col-sm-2"></div>
          </div>
        </div>
        <div>
            <canvas id="myChart" width="400px" height="400px"></canvas>
        </div>
      </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.2.1/dist/chart.min.js" integrity="sha256-uVEHWRIr846/vAdLJeybWxjPNStREzOlqLMXjW/Saeo=" crossorigin="anonymous"></script>
        <script>
            const labels = [
              'Entre 0 et 8 de moyenne',
              'Entre 8 et 12 de moyenne',
              'Entre 12 et 17 de moyenne',
              'Entre 17 et 20 de moyenne',
            ];

            const data = {
                labels: labels,
                datasets: [
                {
                    label: 'Mauvais',
                    data: ['${mauvais}', 0, 0, 0],
                    backgroundColor:  'rgba(240,173,78,0.9)',
                    borderColor: 'rgba(240,173,78,1)',
                    borderWidth: 1,
                },
                {
                    label: 'Moyen',
                    data: [0, '${moyen}', 0, 0],
                    backgroundColor:  'rgba(217,83,79,0.9)',
                    borderColor: 'rgba(217,83,79,1)',
                    borderWidth: 1,
                },
                {
                    label: 'bon',
                    data: [0, 0, '${bon}', 0],
                    backgroundColor:  'rgba(92,184,92,0.9)',
                    borderColor: 'rgba(92,184,92,1)',
                    borderWidth: 1,
                },
                {
                    label: 'excellent',
                    data: [0, 0, 0, '${excellent}'],
                    backgroundColor:  'rgba(2,117,216,0.9)',
                    borderColor: 'rgba(2,117,216,1)',
                    borderWidth: 1,
                }
            ]
            };

            var config = {
                type : 'bar',
                data : data,
                options : {
                    responsive: true,
                    plugins : {
                        title : {
                            text : 'Classement des étudiants par moyenne',
                            color : 'black',
                            display : true,
                            font : {
                                size : 20,
                            }
                        },
                        legend : {
                            display : true,
                            labels : {
                                color : 'black',
                            }
                        }
                    },
                    scales : {
                        y : {
                            beginAtZero: true,
                            grid : {
                                color : 'rgba(0,0,0,0.4)',
                            },
                            ticks: {
                                color : 'black',
                            },
                            title: {
                                text: 'Pourcentage',
                                color: 'black',
                                font : {
                                    size: 15,
                                },
                                display : true,
                            }
                        },
                        x : {
                            grid : {
                                color : 'rgba(0,0,0,0.4)',
                            },
                            ticks: {
                                color : 'black',
                            },
                            title: {
                                text: 'Niveau',
                                color: 'black',
                                font : {
                                    size: 15,
                                },
                                display : true,
                            }
                        },

                    }
                }
            };
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, config);
        </script>
<jsp:include page="footer.jsp" />