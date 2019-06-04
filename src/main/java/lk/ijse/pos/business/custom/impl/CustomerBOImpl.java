package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {

        Customer customer = customerDAO.getOne(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
        return customerDTO;

    }

    public List<CustomerDTO> getAllCustomers() throws Exception {

        List<CustomerDTO> customers = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());

        return customers;
    }

    public void saveCustomer(CustomerDTO dto) throws Exception {

        customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));

    }

    public void updateCustomer(CustomerDTO dto) throws Exception {

        customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));

    }

    public void removeCustomer(String id) throws Exception {

        customerDAO.deleteById(id);

    }

}
