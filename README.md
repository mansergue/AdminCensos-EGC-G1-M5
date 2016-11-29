# CensoEGC
Se usará este repositorio para el desarrollo del módulo "Administración y gestión de censos" de AgoraUS, tanto para código como para la documentación del mismo.

- Gestión de código:
  Para la gestión del código del proyecto hemos planteado varias posibilidades, dependiendo de la que prefiera cada miembro o el sistema operativo que use(todas tendrán en común el uso de git como repositorio, solo cambiaría la forma de realizar los commit):
  1. Uso del plugin EGit en Eclipse con ubuntu. Esta es una manera más rapida e intuitiva que por línea de comandos, ya que con dos simples clics podremos hacer commit, crear ramas, etc.
  Por ejemplo tenemos las siguientes equivalencias entre EGit y línea de comandos: 
      - Commit: git commit –a; Push Branch “nombre_rama”: git push; Pull: git pull; Switch to new branch: git branch nombre_nueva_rama; Switch to “nombre_rama_existente”: git checkout nombre_rama_existente; Merge: git merge “nombre_rama”; Add to Index: git add “nombre_archivo”; Show in History: git log.
  2. Uso del plugin EGit en Eclipse con windows xp(máquina virtual de DP). Esta forma es prácticamente idéntica al uso del plugin en ubuntu, ya que solamente cambia el sistema operativo. 
  3. Línea de comandos con gitbash en windows 10 o windows 7. De esta forma la gestión se hará en el sistema operativo nativo, con la desventaja de que los comandos deberán de ser todos escritos. Usamos también gitGUI para hacer los merge,crear ramas y subir algunos archivos.
  4. Realizar el commit-push directamente en github. Este procedimiento se realizara para cambios en archivos de menor importancia, como el Readme.md por ejemplo. Se realizará el cambio con el editor de github directamente, y se pondrá un título y comentario para el commit.
  
  
  
- Gestión de incidencias:
  Consta de tres fases claramente diferenciadas.
  
  1. Análisis: En esta fase se estudian aspectos como el origen o la posible causa de la incidencia y su alcance o clasificación. Esta fase inicial puede considerarse continua en todo el proceso pues cualquier avance suficientemente importante en la resolución debe conducir a una actualización del análisis. En cuanto a los grados de alcance usaremos los siguientes, siendo 1 el mayor nivel de prioridad y 7 el menor: 
  
      1.-Bloqueador: inhibe la continuidad de desarrollo o pruebas del programa
      2.-Crítico: Crash de la aplicación, pérdida de datos o fuga de memoria severa.
      3.-Mayor: pérdida mayor de funcionalidad, como menús inoperantes, datos de salida extremadamente incorrectos, o dificultades que                  inhiben parcial o totalmente el uso del programa.
      4.-Normal: Una parte menor del componente no es funcional.
      5.-Menor: Una pérdida menor de funcionalidad, o un problema al cual se le puede dar la vuelta
      6.-Trivial: Un problema cosmético, como puede ser una falta de ortografía o un texto desalineado.
      7.-Mejora: Solicitud de una nueva característica o funcionalidad.
      (fuente: https://es.wikipedia.org/wiki/Sistema_de_seguimiento_de_errores)
   
   2.  Resolución: En esta fase se invierten horas de trabajo en buscar y aplicar posibles soluciones a los problemas posteriormente analizados. Como ya mencionamos antes se puede reanalizar cualquier incidencia si se considera necesario.
   
   3. Cierre: Corresponde a la fase final en la cual se habrían solventado los problemas y se procede a cerrar la correspondiente incidencia documentando brevemente la solución aplicada y/o posibles metodologías que se podrían usar en adelante para evitar problemas similares.
