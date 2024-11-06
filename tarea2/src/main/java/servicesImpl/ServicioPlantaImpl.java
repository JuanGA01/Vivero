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

	@Override
	public Planta BuscarPlantaXId(Planta planta) {
		for (Planta p : plantaDAO.findAll()) {
	        if (p.getCodigo().equals(planta.getCodigo())) {
	            return p;
	        }
	    }
	    return null;
	}
	
	@Override
	public boolean existePlanta(Planta planta) {
		for(Planta p : plantaDAO.findAll()) {
			if (planta.getCodigo().equals(p.getCodigo())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String InsertarPlanta(Planta planta) {
		if (existePlanta(planta)) {
			plantaDAO.update(planta);
			return "Planta actualizada exitosamente";
		}else {
			plantaDAO.insert(planta);
			return "Planta insertada exitosamente";
		}
	}





}
