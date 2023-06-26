package com.example.biblioteca.recyclerview

data class Libro(
    val titulo: String,
    val autor: String,
    val descripcion: String,
    val genero: String,

    ) {
    companion object {
        val data
            get() = listOf(
                Libro(
                    "Cien años de soledad",
                    "Gabriel García Márquez",
                    "Cien años de soledad trata un siglo en la vida de la familia Buendía, cuyo patriarca, José Arcadio Buendía, fundó el pueblo ficticio de Macondo, en Colombia. La novela es considerada una obra maestra de la literatura latinoamericana y uno de los ejemplos clásicos del realismo mágico.",
                    "Novela"
                ),
                Libro(
                    "1984",
                    "George Orwell",
                    "Es una novela de distopía cuya trama ocurre en Oceanía, un país dominado por un gobierno totalitario que mantiene en constante vigilancia a sus ciudadanos e, incluso, insiste en espiar sus pensamientos para mantener el orden.",
                    "Ciencia ficción"
                ),
                Libro(
                    "El señor de los anillos",
                    "J. R. R. Tolkien",
                    "La novela narra el viaje del protagonista principal, Frodo Bolsón, hobbit de la Comarca, para destruir el Anillo Único y la consiguiente guerra que provocará el enemigo para recuperarlo, ya que es la principal fuente de poder de su creador, el Señor oscuro Sauron.",
                    "Literatura fantástica"
                ),
                Libro(
                    "Crónica de una muerte anunciada",
                    "Gabriel García Márquez",
                    "Crónica de una muerte anunciada es una novela corta de Gabriel García Márquez, publicada en 1981. Tomando elementos del realismo mágico y del relato policial, la novela cuenta la muerte de Santiago Nasar a manos de los hermanos Vicario. La obra está inspirada en un crimen real que tuvo lugar en Colombia.",
                    "Novela"
                ),
                Libro(
                    "Rebelión en la granja",
                    "George Orwell",
                    "es una novela satírica de George Orwell acerca de un grupo de animales en una granja que expulsan a los humanos y crean un sistema de gobierno propio que acaba convirtiéndose en una tiranía brutal.",
                    "Novela"
                ),
                Libro(
                    "Don Quijote de la Mancha",
                    "Miguel de Cervantes",
                    "El libro relata las aventuras y desventuras de un hidalgo de 50 años llamado Alonso Quijano, quien decide ser un caballero andante como aquellos que aparecen en sus libros de caballerías favoritos.",
                    "Novela"
                ),
                Libro(
                    "Un mundo feliz",
                    "Aldous Huxley",
                    "Un mundo feliz describe un mundo utópico, irónico y ambiguo donde la humanidad es permanentemente feliz, donde no existen guerras ni pobreza y las personas son desinhibidas, tienen buen humor, son saludables y tecnológicamente avanzadas.",
                    "Novela"
                ),
                Libro(
                    "ELANTRIS",
                    "Brandon Sanderson",
                    "Antaño famosa sede de inmortales, un lugar repleto de poder y magia, Elantris ha caído en desgracia. Ahora sólo acoge a los nuevos 'muertos en vida'postrados en una insufrible 'no-vida' tras una misteriosa y terrible 'transformación'.",
                    "Literatura fantástica"
                ),
                Libro(
                    "En las montañas de la locura",
                    "H.P. Lovecraft",
                    "En las montañas de la locura narra la expedición desastrosa que realizan unos grupo de expertos a la Antártida, en el que descubren unos seres biológicamente extraordinarios que llegaron a la tierra desde otro astro mucho antes de la aparición de la humanidad, y que fueron casi extinguidos por una especie de robots",
                    "Ciencia ficción"
                ),
                Libro(
                    "El camino de los reyes",
                    "Brandon Sanderson",
                    "El libro comienza con una victoria. Miramos la vida de los Heraldos , los líderes de los Caballeros Radiantes . Durante miles de años, los Heraldos han librado una guerra contra una raza de monstruos, llamados Portadores del Vacío , para proteger a la humanidad.",
                    "Literatura fantástica"
                ),
                Libro(
                    "La paciente silenciosa",
                    "Alex Michaelides",
                    "Alicia Berenson, una pintora de éxito, dispara cinco tiros en la cabeza de su marido, y no vuelve a hablar nunca más. Su negativa a emitir palabra alguna convierte una tragedia doméstica en un misterio que atrapa la imaginación de toda Inglaterra.",
                    "Misterio"
                ),
                Libro(
                    "Las Doncellas",
                    "Alex Michaelides",
                    "David Baldacci A sus treinta y seis años, Mariana intenta recuperarse de la pérdida de Sebastian, el gran amor de su vida, ahogado durante unas vacaciones en una isla griega. Ella trabaja en Londres como terapeuta, pero cuando su sobrina Zoe, la única familia que le queda, la llama desde Cambridge para contarle que Tara, su mejor amiga, ha sido brutalmente asesinada cerca de la residencia de estudiantes, decide acudir en su ayuda.",
                    "Misterio"
                ),
                Libro(
                    "Un mundo feliz",
                    "Aldous Huxley",
                    "Un mundo futurista, utópico, altamente regulado y tecnológico. Arranca con la visita por parte de un grupo de estudiantes al Centro de Incubación y Condicionamiento de Londres. Allí, el director de la fábrica explica a los jóvenes cómo se divide a la población, ya desde su incubación. Así, según su condición genética, previamente alterada, la sociedad se divide desde los Alpha hasta los Epsilon, de mayor a menor inteligencia.",
                    "Ciencia ficción"
                ),
                Libro(
                    "Los hijos de Anansí",
                    "Neil Gaiman",
                    "Tras enterarse de la muerte de su padre, Gordo Charlie Nancy vuela a Florida a su entierro. Tras ello, una vieja amiga de la familia le comunica que no sólo su padre era un Dios, Anansi, sino que además tiene un hermano. Y que cuando quiera saber de él, sólo tiene que preguntarle a las arañas. Anansi, el dios araña, es el dueño de los cuentos. Se los robó un día al Tigre, que desde entonces busca venganza por la humillación a que le sometió Anansi.",
                    "Literatura fantástica"
                ),
                Libro(
                    "Crimen y castigo",
                    "Fiódor Dostoyevski",
                    "La historia narra la vida de Rodión Raskólnikov, un estudiante en la capital de la Rusia Imperial, San Petersburgo. Este joven se ve obligado a suspender sus estudios por la miseria en la cual se ve envuelto, a pesar de los esfuerzos realizados por su madre Pulqueria y su hermana Dunia para enviarle dinero.",
                    "Novela"
                ),

                )
    }
}



