package pokemon.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pokemon.obj.Pokemon;

@Path("/")
public class PokedexService {

	private static List<Pokemon> pokedex = new ArrayList<Pokemon>();

	@POST
	@Consumes("./PokedexJava/pokedex.json")
	@Produces(MediaType.APPLICATION_JSON)
	public Pokemon postPokemon(Pokemon newPokemon) {

		if (newPokemon.getName() == null || newPokemon.getName().equals("") || newPokemon.getName().equals(" ")
				|| newPokemon.getNum() == null)
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
					.entity("Campos name, ID, e número não podem ser nulos").build());

		for (Pokemon pokemon : pokedex) {
			if (newPokemon.getId() == pokemon.getId() || newPokemon.getNum().equals(pokemon.getNum())) {
				throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
						.entity("Pokemon com mesma ID ou número já existe").build());
			}
		}
		pokedex.add(newPokemon);

		return newPokemon;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("./PokedexJava/pokedex.json/{pokemonNum}")
	public Pokemon getPokemon(@PathParam("pokemonNum") String num) {
		Pokemon pokemon = null;

		for (Pokemon poke : pokedex) {
			if (poke.getNum().toLowerCase().equals(num.toLowerCase())) {
				pokemon = poke;
				return pokemon;
			}
		}

		throw new WebApplicationException(404);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("./PokedexJava/pokedex.json/{pokemonNum}")
	public void putPokemon(String numPokemon) {
		Pokemon pokemon = null;
		for (int i = 0; i <= pokedex.size(); i++) {
			pokemon = pokedex.get(i);
			if (pokemon.getNum().equals(numPokemon)) {
				pokedex.remove(i);
				pokedex.add(pokemon);
			}
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("./PokedexJava/pokedex.json/{pokemonNum}")
	public void deletePokemon(String numPokemon) {
		Pokemon pokemon = null;
		for (int i = 0; i <= pokedex.size(); i++) {
			pokemon = pokedex.get(i);
			if (pokemon.getNum().equals(numPokemon)) {
				pokedex.remove(i);
			}
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("./PokedexJava/pokedex.json/{pokemonNum}")
	public List<Pokemon> getPokemons() {
		if (!pokedex.isEmpty()) {
			return pokedex;
		} else {
			throw new WebApplicationException(404);
		}
	}

}
