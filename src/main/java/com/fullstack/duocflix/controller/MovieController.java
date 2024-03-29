package com.fullstack.duocflix.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.duocflix.model.Movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/movies")
public class MovieController {
    private List<Movie> movieList = new ArrayList<>();

    public MovieController() {
        // peliculas.add(new Pelicula(1, "Atrapados en lo profundo", 2024, "Claudio Fäh", "Acción",
        //         "Personajes de muy diferentes orígenes se juntan cuando el avión en el que viajan se estrella en el Océano Pacífico. Cuando el avión de pasajeros se detiene peligrosamente cerca del borde de un barranco con los pasajeros y la tripulación supervivientes atrapados en una bolsa de aire, se produce una lucha de pesadilla por la supervivencia con el suministro de aire agotándose y los peligros acechando por todos lados."));
        // peliculas.add(new Pelicula(2, "Malas costumbres", 2024, "Pablo Mantilla", "Comedia",
        //         "Inspirada en hechos reales que transcurren en junio de 2016, en un Chile eufórico y paralizado por completo mientras la selección chilena de fútbol disputaba la Copa América Centenario. Es en medio de este fervor que dos hermanos tienen la oportunidad de cobrar la cuantiosa herencia que ha dejado su madre. El detalle, un padre que está desaparecido hace 20 años, del cual se desconoce su paradero y tendría derecho a ella también. En la desesperación por cobrar la millonaria herencia dejando fuera al “papito corazón” que los abandonó, estos hermanos están dispuestos a absolutamente todo por conseguir la totalidad del dinero, lo que los lleva a sumergirse en una hilarante serie de sucesos, hartos de un sistema que los oprime."));
        // peliculas.add(new Pelicula(3, "El bastardo", 2023, "Nikolaj Arcel", "Aventuras, Drama",
        //         "En 1755, el empobrecido capitán Ludvig Kahlen se dispone a conquistar los duros e inhóspitos páramos daneses con un objetivo aparentemente imposible: crear una colonia en nombre del rey. A cambio, recibirá un nombre real que anhela con desesperación. Sin embargo, el único gobernante de la zona, el despiadado Frederik de Schinkel, cree arrogantemente que esa tierra le pertenece. Cuando de Schinkel se percata de que su criada Ann Barbara y su servil marido han escapado para refugiarse con Kahlen, el privilegiado y rencoroso gobernante jura venganza y promete hacer todo lo que esté a su alcance para ahuyentar al capitán. Pero Kahlen no se deja intimidar y emprende una batalla tan desigual que pondrá en riesgo no solo su vida, sino también a la familia de personas marginadas que se ha formado a su alrededor."));
        // peliculas.add(new Pelicula(4, "Baghead: Contacto con la muerte", 2024, "Alberto Corredor", "Terror",
        //         "El terror ataca cuando un hombre afligido busca la ayuda de una bruja que cambia de forma para comunicarse con los muertos. Largometraje basado en el corto del mismo nombre (2017)."));
        // peliculas.add(new Pelicula(5, "El hombre de los sueños", 2023, "Kristoffer Borgli", "Comedia",
        //         "Paul Matthews, un desventurado padre de familia, ve cómo su vida da un vuelco cuando millones de extraños empiezan a verle en sueños. Pero cuando sus apariciones nocturnas toman un giro de pesadilla, Paul se ve obligado a navegar por su nuevo estrellato."));
    }

    @GetMapping
    public List<Movie> getMovieList() {
        return movieList;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        for (Movie p : movieList) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

}
