
CREATE TABLE movie (
    id NUMBER(19,0) GENERATED ALWAYS AS IDENTITY, 
    "year" NUMBER(10,0), 
    director VARCHAR2(255 CHAR), 
    genre VARCHAR2(255 CHAR), 
    title VARCHAR2(255 CHAR), 
    synopsis CLOB 
)

ALTER TABLE movie ADD CONSTRAINT movie_pk PRIMARY KEY ( id );


truncate table movie;

INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('El viaje de Chihiro', 2001, 'Hayao Miyazaki', 'Animación. Fantástico. Aventuras', 'Chihiro es una niña de diez años que viaja en coche con sus padres. Después de atravesar un túnel, llegan a un mundo fantástico, en el que no hay lugar para los seres humanos, sólo para los dioses de primera y segunda clase. Cuando descubre que sus padres han sido convertidos en cerdos, Chihiro se siente muy sola y asustada.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('La tumba de las luciérnagas', 1988, 'Isao Takahata', 'Animación. Drama. Bélico', 'Segunda Guerra Mundial (1939-1945). Seita y Setsuko son hijos de un oficial de la marina japonesa que viven en Kobe. Un día, durante un bombardeo, no consiguen llegar a tiempo al búnker donde su madre los espera. Cuando después buscan a su madre, la encuentran malherida en la escuela, que ha sido convertida en un hospital de urgencia.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('La princesa Mononoke', 1997, 'Hayao Miyazaki', 'Animación. Fantástico. Aventuras', 'Con el fin de curar la herida que le ha causado un jabalí enloquecido, el joven Ashitaka sale en busca del dios Ciervo, pues sólo él puede liberarlo del sortilegio. A lo largo de su periplo descubre cómo los animales del bosque luchan contra hombres que están dispuestos a destruir la Naturaleza.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('Akira', 1988, 'Katsuhiro Ōtomo', 'Animación. Ciencia ficción. Acción. Thriller', 'Año 2019. Neo-Tokyo es una ciudad construida sobre las ruinas de la antigua capital japonesa destruida tras la Tercera Guerra Mundial. Japón es un país al borde del colapso que sufre continuas crisis políticas. En secreto, un equipo de científicos ha reanudado por orden del ejército un experimento para encontrar a individuos que puedan controlar el arma definitiva: una fuerza denominada "la energía absoluta". Pero los habitantes de Neo-Tokyo tienen otras cosas de las que preocuparse. Uno de ellos es Kaneda, un joven pandillero líder de una banda de motoristas. Durante una pelea, su mejor amigo, Tetsuo, sufre un extraño accidente y termina ingresado en unas instalaciones militares. Allí los científicos descubrirán que es el poseedor de la energía absoluta. Pero Tetsuo, que no se resigna a convertirse en un conejillo de indias, muy pronto se convertirá en la amenaza más grande que el mundo ha conocido.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('Ghost in the Shell', 1995, 'Mamoru Oshii', 'Animación. Ciencia ficción. Acción', 'Año 2029. En una enorme ciudad asiática, una mujer robot policía -cyborg- investiga las siniestras actividades de un misteriososo hacker, un supercriminal que está invadiendo las autopistas de la información.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('Los niños lobo', 2001, 'Mamoru Hosoda', 'Animación. Drama. Fantástico', 'Cuando era poco más que una adolescente, Hana se enamoró de un Hombre Lobo. Puede parecer extraño, pero durante años fueron inmensamente felices, y tuvieron dos hijos: Yuki y Ame, que nacieron también con la capacidad de convertirse en lobos. Tras la repentina muerte de su compañero, Hana decide mudarse al campo para así criar a sus hijos en un entorno tranquilo, donde sus extraordinarias facultades no sean descubiertas. Sin embargo, al crecer, Yuki y Ame deberán decidir si quieren vivir como humanos o como lobos.');
INSERT INTO MOVIE (title, year, director, genre, synopsis) VALUES('Paprika, detective de los sueños', 2006, 'Satoshi Kon', 'Animación. Ciencia ficción. Fantástico. Intriga', 'La psiquiatra Atsuko Chiba ha desarrollado un método de terapia revolucionario denominado "PT", un prototipo de máquina experimental gracias a la cual es posible introducirse en la mente de los pacientes para tratar sus ansiedades. Pero uno de los modelos de PT es robado del laboratorio de la Dra. Atsuko, y comienzan a utilizarlo para invadir las mentes de sus creadores, destruyendo sus personalidades mientras duermen.');

