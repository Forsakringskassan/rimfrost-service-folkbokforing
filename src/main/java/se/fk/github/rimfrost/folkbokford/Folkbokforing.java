package se.fk.github.rimfrost.folkbokford;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.FolkbokforingControllerApi;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.Adress;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.FolkbokforingPersnrGet200Response;
import se.fk.rimfrost.api.folkbokforing.jaxrsspec.controllers.generatedsource.model.Kon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/folkbokforing/{persnr}")
public class Folkbokforing implements FolkbokforingControllerApi
{
   private static final Logger log = LoggerFactory.getLogger(Folkbokforing.class);

   @Override
   public FolkbokforingPersnrGet200Response folkbokforingPersnrGet(
         @PathParam("persnr") @Pattern(regexp = "^\\d{8}-\\d{4}$") String personnummer)
   {
      log.info("Folkbokforing received request: personnummer={}", personnummer);
      if (personnummer.endsWith("9999"))
      {
         log.warn("Personnummer not found: {}", personnummer);
         throw new NotFoundException("Person not found");
      }
      var response = new FolkbokforingPersnrGet200Response();

      response.setId(personnummer);
      response.setFornamn("Lisa");
      response.setEfternamn("Tass");
      response.setKon(Kon.K);

      var adress = new Adress();
      adress.setCareOf("C/o Andersson");
      adress.setPostnummer("12345");
      adress.setPostort("Luleå");
      adress.setUtdelningsadress("Försäkringsgatan 137");

      response.setAdress(adress);
      log.info("Folkbokforing sending response: {}", response);
      return response;
   }
}
