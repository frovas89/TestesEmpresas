package br.com.frovas.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.frovas.GRAController;
import br.com.frovas.dto.FinalProducerIntervalDTO;
import flexjson.JSONSerializer;

@Path("farmacia")
public class GRAResource {


	@GET
	@Path("produtoresmaxemin/")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@GZIP
	public Response listMinMaxWinners() {

		try {

			FinalProducerIntervalDTO finalDTO = GRAController.listMinMaxWinners();

			JSONSerializer json = new JSONSerializer();
			String resposta = json.serialize(finalDTO);
			return Response.status(Status.OK).entity(resposta).build();
		} catch (Exception e) {
			String resposta = "Ocorreu um erro ao listar Produtores!";
			return Response.status(Status.NOT_FOUND).entity(resposta).build();
		}

//		try {
//			// confirma se as 3 informações pertencem ao mesmo usuário
//			if (Facade.validarCredenciaisUsuarioFarmacia(Integer.valueOf(numInterno), cpf, cns)) {
//				SolicitacaoFacade solicitacaoFacade = new SolicitacaoFacade();
//				UsuarioFarmaciaBasicoDTO usuarioFarmacia = solicitacaoFacade.obterUsuarioFarmacia(Integer.valueOf(numInterno), cpf, cns);
//				JSONSerializer json = new JSONSerializer().transform(DATE_TRANSFORMER, Date.class);
//				String resposta = json.serialize(usuarioFarmacia);
//				return Response.status(Status.OK).entity(resposta).build();
//			} else {
//				String resposta = montarMensagemErroMobileDTOSerializado(Status.NOT_FOUND.getStatusCode(), Status.NOT_FOUND.toString(), ERRO_VALIDAR_USUARIO);
//				return Response.status(Status.NOT_FOUND).entity(resposta).build();
//			}
//
//		} catch (Exception e) {
//			return trataExcecaoWebService(e);
//		}
	}
}
