package com.vacation.booking.bootstrap;

import com.vacation.booking.dao.CustomerRepository;
import com.vacation.booking.dao.DivisionRepository;
import com.vacation.booking.entity.Customer;
import com.vacation.booking.entity.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    // 2D array of sample customer data
    // Columns: firstName, lastName, address, postalCode, phone, divisionId
    private static final String[][] SAMPLE_CUSTOMERS = {
            {"Alice", "Smith",    "123 Main St",       "10001", "555-111-1111", "31"},
            {"Bob",   "Johnson",  "456 Sunset Blvd",   "90001", "555-222-2222", "4" },
            {"Carol", "Williams", "789 Lone Star Ave", "73301", "555-333-3333", "42"},
            {"David", "Brown",    "321 Palm Dr",       "33101", "555-444-4444", "9" },
            {"Eve",   "Davis",    "654 Lakeview Rd",   "60601", "555-555-5555", "12"},
    };

    public BootStrapData(CustomerRepository customerRepository,
                         DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Customer> existingCustomers = customerRepository.findAll();

        // Loop through each sample customer and check if it already exists in the db
        for (String[] data : SAMPLE_CUSTOMERS) {
            String firstName = data[0];
            String lastName  = data[1];

            boolean alreadyExists = false;

            for (Customer existing : existingCustomers) {
                if (existing.getFirstName().equals(firstName) &&
                        existing.getLastName().equals(lastName)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (alreadyExists) {
                System.out.println("Customer already exists, skipping: " + firstName + " " + lastName);
                continue;
            }

            // Customer does not exist, create and save it
            String address    = data[2];
            String postalCode = data[3];
            String phone      = data[4];
            Long divisionId   = Long.parseLong(data[5]);

            Division division = divisionRepository.findById(divisionId)
                    .orElseThrow(() -> new RuntimeException("Division not found: " + divisionId));

            Customer customer = new Customer(division, address, firstName, lastName, phone, postalCode);
            customerRepository.save(customer);

            System.out.println("Saved customer: " + firstName + " " + lastName);
        }

        System.out.println("Bootstrap complete. Total customers: " + customerRepository.count());
    }
}