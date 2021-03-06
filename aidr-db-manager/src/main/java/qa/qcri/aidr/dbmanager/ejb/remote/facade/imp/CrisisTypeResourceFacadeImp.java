package qa.qcri.aidr.dbmanager.ejb.remote.facade.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import qa.qcri.aidr.dbmanager.dto.CollectionDTO;
import qa.qcri.aidr.dbmanager.dto.CrisisTypeDTO;
import qa.qcri.aidr.dbmanager.ejb.local.facade.impl.CoreDBServiceFacadeImp;
import qa.qcri.aidr.dbmanager.ejb.remote.facade.CrisisTypeResourceFacade;
import qa.qcri.aidr.dbmanager.entities.misc.CrisisType;

/**
*
* Implements operations for managing the crisis_type table of the aidr_predict DB
* 
* @author Koushik
*
*/

@Stateless(name = "CrisisTypeResourceFacadeImp")
public class CrisisTypeResourceFacadeImp extends CoreDBServiceFacadeImp<CrisisType, Long> implements CrisisTypeResourceFacade {

	private static final Logger logger = Logger.getLogger("db-manager-log");

	public CrisisTypeResourceFacadeImp() {
		super(CrisisType.class);
	}

	@Override
	public List<CrisisTypeDTO> getAllCrisisTypes() {
		List<CrisisTypeDTO> crisisTypeDTOList = new ArrayList<CrisisTypeDTO>();
		List<CrisisType> crisisTypeList = getAll();
		for (CrisisType cType : crisisTypeList) {
			crisisTypeDTOList.add(new CrisisTypeDTO(cType));
		}
		return crisisTypeDTOList;
	}

	@Override
	public CrisisTypeDTO addCrisisType(CrisisTypeDTO crisisType) {
		try {
			CrisisType cType = crisisType.toEntity();
			em.persist(cType);
			em.flush();
			em.refresh(cType);
			return new CrisisTypeDTO(cType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrisisTypeDTO editCrisisType(CrisisTypeDTO crisisType) {
		try {
			CrisisType cType = getById(crisisType.getCrisisTypeId());
			if (cType != null) {
				cType = em.merge(crisisType.toEntity());
				em.flush();
				em.refresh(cType);
				logger.info("Updated crisisType: " + cType.getName() + ", " + cType.getCrisisTypeId());
				return cType != null ? new CrisisTypeDTO(cType) : null;
			} else {
				logger.error("Not found");
				throw new RuntimeException("Not found");
			}
		} catch (Exception e) {
			logger.error("Exception in merging/updating crisisType: " + crisisType.getCrisisTypeId(), e);
		}
		return null;
	}

	@Override
	public Integer deleteCrisisType(Long id) {
		CrisisType crisisType = getById(id);
		if (crisisType != null) {
			this.delete(crisisType);
			return 1;
		} else {
			logger.error("CrisisType requested to be deleted does not exist! id = " + id);
			throw new RuntimeException("CrisisType requested to be deleted does not exist! id = " + id);
		}
	}

	@Override
	public List<CrisisTypeDTO> findByCriteria(String columnName, Object value) {
		List<CrisisType> list = getAllByCriteria(Restrictions.eq(columnName, value));
		List<CrisisTypeDTO> dtoList = new ArrayList<CrisisTypeDTO>();
		if (list != null && !list.isEmpty()) {
			for (CrisisType c : list) {
				dtoList.add(new CrisisTypeDTO(c));
			}
		}
		return dtoList;
	}

	@Override
	public CrisisTypeDTO findCrisisTypeByID(Long id) {
		CrisisType c = getById(id);
		return c != null ? new CrisisTypeDTO(c) : null;
	}

	@Override
	public boolean isCrisisTypeExists(Long id) {
		CrisisType c = getById(id);
		return c != null ? true : false;
	}

	@Override
	public List<CollectionDTO> getAllCrisisForCrisisTypeID(Long id) {
		List<CollectionDTO> dtoList = new ArrayList<CollectionDTO>();
		return dtoList;
	}

}
