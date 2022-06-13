package pokemon.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import pokemon.obj.Pokemon;

@Path("/")
public class PokedexService {

	private static List<Pokemon> pokelist = new ArrayList<Pokemon>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{pokemonNum}")
	public Pokemon getPokemon(@PathParam("pokemonNum") String num) {
		Pokemon pokereturn = null;

		for (Pokemon pokemon : pokelist) {
			if (pokemon.getNum().toLowerCase().equals(num.toLowerCase())) {
				pokereturn = pokemon;
				return pokereturn;
			}
		}

		throw new WebApplicationException(404);
	}

}
