package Mapper;

import java.util.List;
import java.util.Map;

import Table.disktable;
import Table.disktableuser;
import Table.userattribute;



public interface mapper {

	
	public void keyfactory(Table.keyfactory keyfactory);
	public List<disktable> selectall();
	public List<disktable> selectbystyle(Map<String, Object> mapset);

	public disktable select(Map<String, Object> mapset);

	public Integer insert(disktable disktable);

	public Integer delete(Integer id);
	public Integer deletepro(Map<String, Object> mapset);
	public disktableuser userCheckSelect(disktableuser disktableuser);
	public void insertNewUser(disktableuser disktableuser);
	public disktableuser selectadmin(disktableuser disktableuser);
	public Integer addnewadmin(disktableuser disktableuser);
	public void insertuserattritute(userattribute userattribute);
	public void createdisktablePro(Map<String, Object> mapset);
	public void createdisktablePro2(Map<String, Object> mapset);
	public userattribute selectuserattributebyuser(disktableuser disktableuser);
	
	public void insertdisktablePro(Map<String, Object> mapset);
	public void updateusedspacesizebyid(userattribute userattribute);
	public List<disktable> selectallpro(Map<String, Object> mapset);
	public List<disktableuser> registercheck(disktableuser disktableuser);
	public disktableuser emailcheck(disktableuser disktableuser);
	public void updatepassword(disktableuser disktableuser);
	public void insertstyle(Map<String, Object> mapset);
	public int shareuserfiletablebyid(Map<String, Object> mapset); 
	public Integer updateshareflag(Map<String, Object> mapset);
	public Integer updateProflag (Map<String, Object> mapset);
	public Integer activecheck(disktableuser disktableuser);
	
}
