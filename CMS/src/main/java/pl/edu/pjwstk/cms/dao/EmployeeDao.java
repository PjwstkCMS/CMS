package pl.edu.pjwstk.cms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import pl.edu.pjwstk.cms.dao.general.GenericDao;
import pl.edu.pjwstk.cms.dto.EmployeeDto;
import pl.edu.pjwstk.cms.models.Address;
import pl.edu.pjwstk.cms.models.Archive;
import pl.edu.pjwstk.cms.models.Card;
import pl.edu.pjwstk.cms.models.Department;
import pl.edu.pjwstk.cms.models.Employee;
import pl.edu.pjwstk.cms.models.PersonData;
import pl.edu.pjwstk.cms.models.Position;

public class EmployeeDao extends GenericDao<Employee> {

    private final static Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

    public EmployeeDao() {
        super(Employee.class);
    }

    public boolean archive(Employee employee) {
        Archive ar = new Archive();
        ArchiveDao arDao = new ArchiveDao();
        ar.setEmployeeId(employee.getId() + "");
        return (arDao.insert(ar) > 0);
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return getEmployeeDtoList(-1L);
    }

    public List<EmployeeDto> getManagerEmployeesDtoList(Long managerId) {
        return getEmployeeDtoList(managerId);
    }

    /**
     * Pobiera wszystkie dane każdego pracownika w formie listy dto. Nie są
     * pobierane numery kart magnetycznych pracowników.
     *
     * @param archive - pobieranie danych aktywnych lub archiwizowanych
     * @return
     */
    public List<EmployeeDto> getEmployeeListDtoWithoutCards(boolean archive) {
        return getEmployeeListDtoWithoutCards(null, archive);
    }

    /**
     * Pobiera wszystkie dane każdego pracownika w formie listy dto. Również
     * pobierane są numery kart magnetycznych pracowników.
     *
     * @param archive - pobieranie danych aktywnych lub archiwizowanych
     * @return
     */
    public List<EmployeeDto> getEmployeeListDtoWithCards(boolean archive) {
        return getEmployeeListDtoWithCards(null, archive);
    }

    /**
     * Metoda pobiera EmployeeDto ze wszystkimi danymi pracownika wraz z
     * numerami kart magnetycznych. Parametr <code>archive</code> decyduje o
     * tym, czy pobierane są dane pracowników archiwizowanych czy aktywnych.
     *
     * @param empId
     * @param archive
     * @return
     */
    public List<EmployeeDto> getEmployeeListDtoWithCards(Long empId, boolean archive) {
        String query = "SELECT emp.id as id, emp.persondataId as persondataId, emp.departmentId as departmentId, "
                + " emp.positionId as positionId, per.forename as forename, per.surname as surname, per.email as email, "
                + " per.phone as phone, emp.salary as salary, per.pesel as pesel, dept.name as department, pos.name as position, "
                + "card.number as cardnumber, card.id as cardid "
                + "FROM employee as emp, persondata as per, position as pos, department as dept, card "
                + "WHERE dept.id=emp.departmentId AND pos.id=emp.positionId AND per.id=emp.persondataId AND card.employeeId=emp.id";
        if (empId != null) {
            query += " AND emp.id=" + empId;
        }
        if (archive) {
            query += " AND emp.id IN (SELECT a.employeeId FROM archive as a)";
        } else {
            query += " AND emp.id NOT IN (SELECT a.employeeId FROM archive as a)";
        }
        query += " GROUP BY id";
        ResultSet set = this.connectionManager.select(query);
        AddressDao addDao = new AddressDao();
        List<Address> adds = addDao.selectForQuery("SELECT * FROM address WHERE persondataId!=-1");
        List<EmployeeDto> empDtos = new ArrayList<>();
        try {
            while (set.next()) {
                EmployeeDto dto = new EmployeeDto();
                dto.setDepartment(set.getString("department"));
                dto.setDepartmentId(set.getLong("departmentId"));
                dto.setEmail(set.getString("email"));
                dto.setForename(set.getString("forename"));
                dto.setId(set.getLong("id"));
                dto.setPersondataId(set.getLong("persondataId"));
                dto.setPesel(set.getString("pesel"));
                dto.setPhone(set.getString("phone"));
                dto.setPosition(set.getString("position"));
                dto.setPositionId(set.getLong("positionId"));
                dto.setSalary(set.getString("salary"));
                dto.setSurname(set.getString("surname"));
                dto.setCardId(set.getLong("cardid"));
                dto.setCardNumber(set.getString("cardnumber"));

                List<Address> empAdds = new ArrayList<>();

                for (Address a : adds) {
                    if (a.getPersondataId().equals(dto.getPersondataId()+"")) {
                        empAdds.add(a);
                    }
                }
                dto.setAddresses(empAdds);
                empDtos.add(dto);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getLocalizedMessage());
        }
        return empDtos;

    }

    /**
     * Metoda pobiera EmployeeDto ze wszystkimi danymi pracownika prócz numerów
     * kart magnetycznych. Parametr <code>archive</code> decyduje o tym, czy
     * pobierane są dane pracowników archiwizowanych czy aktywnych.
     *
     * @param id
     * @param archive
     * @return
     */
    public List<EmployeeDto> getEmployeeListDtoWithoutCards(Long id, boolean archive) {
        String query = "SELECT emp.id as id, emp.persondataId as persondataId, emp.departmentId as departmentId,"
                + " emp.positionId as positionId, per.forename as forename, per.surname as surname, per.email as email,"
                + " per.phone as phone, emp.salary as salary, per.pesel as pesel, dept.name as department,"
                + " pos.name as position"
                + " FROM employee as emp, persondata as per, position as pos, department as dept"
                + " WHERE dept.id=emp.departmentId AND pos.id=emp.positionId AND per.id=emp.persondataId";
        if (id != null) {
            query += " AND emp.id=" + id;
        }
        if (archive) {
            query += " AND emp.id IN (SELECT a.employeeId FROM archive as a)";
        } else {
            query += " AND emp.id NOT IN (SELECT a.employeeId FROM archive as a)";
        }
        ResultSet set = this.connectionManager.select(query);
        AddressDao addDao = new AddressDao();
        List<Address> adds = addDao.selectForQuery("SELECT * FROM address WHERE persondataId!=-1");
        List<EmployeeDto> empDtos = new ArrayList<>();
        try {
            while (set.next()) {
                EmployeeDto dto = new EmployeeDto();
                dto.setDepartment(set.getString("department"));
                dto.setDepartmentId(set.getLong("departmentId"));
                dto.setEmail(set.getString("email"));
                dto.setForename(set.getString("forename"));
                dto.setId(set.getLong("id"));
                dto.setPersondataId(set.getLong("persondataId"));
                dto.setPesel(set.getString("pesel"));
                dto.setPhone(set.getString("phone"));
                dto.setPosition(set.getString("position"));
                dto.setPositionId(set.getLong("positionId"));
                dto.setSalary(set.getString("salary"));
                dto.setSurname(set.getString("surname"));
                List<Address> empAdds = new ArrayList<>();

                for (Address a : adds) {
                    if (a.getPersondataId().equals(dto.getPersondataId()+"")) {
                        empAdds.add(a);
                    }
                }
                dto.setAddresses(empAdds);
                empDtos.add(dto);
            }
        } catch (Exception e) {
            LOGGER.warning(e.getLocalizedMessage());
        }
        return empDtos;

    }

    private List<EmployeeDto> getEmployeeDtoList(Long manager) {
        String query = "SELECT emp.departmentId as departmentId, emp.id as id, emp.persondataId as persondataId, "
                + "emp.positionId as positionId, emp.salary as salary ";
        query += "FROM employee as emp ";

        DepartmentDao depDao = new DepartmentDao();
        List<Department> deps = depDao.selectAll();

        List<Department> managDep = getManagerDepartments(deps, manager + "");
        if (managDep.size() > 0) {
            query += "WHERE";
            for (int i = 0; i < managDep.size(); i++) {
                if (i < managDep.size() && i > 0) {
                    query += " OR ";
                } else {
                    query += " ";
                }
                query += "departmentId='" + managDep.get(i) + "'";

            }

        }

        ResultSet set = this.connectionManager.select(query);
        PersonDataDao personDao = new PersonDataDao();
        CardDao carDao = new CardDao();
        AddressDao addDao = new AddressDao();
        List<EmployeeDto> empDtos = new ArrayList<>();
        List<PersonData> persons = personDao.selectAll();

        PositionDao posDao = new PositionDao();
        List<Position> pos = posDao.selectAll();

        List<Card> cards = carDao.selectAll();
        try {
            while (set.next()) {
                EmployeeDto dto = new EmployeeDto();
                PersonData person = getPersonData(set.getString("persondataId"), persons);
                dto.setPersondataId(Long.parseLong(set.getString("persondataId")));
                dto.setId(set.getLong("id"));
                dto.setForename(person.getForename());
                dto.setSurname(person.getSurname());
                dto.setEmail(person.getEmail());
                dto.setPhone(person.getPhone());
                dto.setPesel(person.getPesel());

                dto.setSalary(set.getString("salary"));
                List<Address> adds = addDao.selectRecordsWithFieldValues("persondataId", dto.getPersondataId());
                dto.setAddresses(adds);

                Department d = getEmpDepartment(deps, set.getString("departmentId"));
                dto.setDepartmentId(d.getId());
                dto.setDepartment(d.getName());

                Position p = getEmpPosition(pos, set.getString("positionId"));
                dto.setPositionId(p.getId());
                dto.setPosition(p.getName());

                Card card = getCard(cards, dto.getId() + "");
                if (card != null) {
                    dto.setCardId(card.getId());
                } else {
                    dto.setCardId(-1L);
                }
                empDtos.add(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return empDtos;
    }

    private Department getEmpDepartment(List<Department> departments, String id) {
        for (Department c : departments) {
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }

    private Position getEmpPosition(List<Position> positions, String id) {
        for (Position c : positions) {
            if (c.getId() == Long.parseLong(id)) {
                return c;
            }
        }
        return null;
    }

    private PersonData getPersonData(String personDataId, List<PersonData> persons) {
        for (PersonData p : persons) {
            if (p.getId() == Long.parseLong(personDataId)) {
                return p;
            }
        }
        return null;
    }

    private Card getCard(List<Card> cards, String empId) {
        for (Card c : cards) {
            if (c.getEmployeeId().equals(empId)) {
                return c;
            }
        }
        return null;
    }

    private List<Department> getManagerDepartments(List<Department> departments, String managerId) {
        List<Department> dep = new ArrayList();
        for (Department c : departments) {
            if (Long.parseLong(c.getManagerId()) == Long.parseLong(managerId)) {
                dep.add(c);
            }
        }
        return dep;
    }
}
