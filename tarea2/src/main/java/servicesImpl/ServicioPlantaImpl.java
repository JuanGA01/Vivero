package servicesImpl;

import com.daoImpl.PlantaDAOImpl;
import com.model.Planta;
import com.services.ServicioPlanta;
import com.utilities.MySqlDAOFactory;

public class ServicioPlantaImpl implements ServicioPlanta {
	
	private MySqlDAOFactory factoria;
	private PlantaDAOImpl plantaDAO;
	
	public ServicioPlantaImpl() {
	    factoria = MySqlDAOFactory.getCon();
	    plantaDAO = (PlantaDAOImpl) factoria.getPlantaDAO();
	}

	public String listaPlantas(){
		String plantas = "";
		if(!plantaDAO.findAll().isEmpty()){
			for (Planta planta : plantaDAO.findAll()) {
				plantas += "\n Código:" +  planta.getCodigo() + ", Nombre científico:" + planta.getNombreCientifico() + ", Nombre Común:" + planta.getNombreComun();
			}
			return plantas;
		}else {
			return "Lista vacía";
		}

	}

}
