package com.oracle.tech.interview.assignment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.oracle.tech.interview.assignment.domain.CustomerDTO;

public class OracleTechAssignment {

	public static void main(String[] args) {

		List<String> readInputData = readInputData(); // Read the input from console. hit enter again if you want to
														// exit from console

		validateInputData(readInputData); // Validate the given input data if mismatch then through exception and exit

		List<CustomerDTO> customerDTOs = convertListOfStringToListOfCustomerDTO(readInputData);

		findUniqueCustomerIdForEachContractId(customerDTOs);

		Map<String, List<Integer>> uniqueCustomerIdForEachGeozone = findUniqueCustomerIdForEachGeozone(customerDTOs);

		System.out.println();

		Map<String, Double> averageOfEachGeozone = customerDTOs.stream().collect(Collectors
				.groupingBy(CustomerDTO::getGeoZone, Collectors.averagingDouble(CustomerDTO::getBuildDuration)));

		averageOfEachGeozone.forEach((geozone, average) -> System.out
				.println("The average buildduration " + average + " for geozone " + geozone));

		System.out.println();

		uniqueCustomerIdForEachGeozone.forEach((geozone, noOfCustomers) -> System.out
				.println("The list of unique customerId " + noOfCustomers + " for geozone " + geozone));

	}

	public static void validateInputData(List<String> readInputData) {

		for (String input : readInputData) {

			String[] splitStr = input.split(",");

			if (splitStr.length != 6) {
				throw new IllegalArgumentException("Given input string invalid " + input
						+ " Format should be in following order customerId,contractId,geozone,teamcode,projectcode,buildduration");
			}

			if (!splitStr[0].matches("[0-9]{7}")) {
				throw new IllegalArgumentException("CustomerId should be 7 digit numeric value");
			}

			if (!splitStr[1].matches("[0-9]{4}")) {
				throw new IllegalArgumentException("ContractId should be 4 digit numeric value");
			}
		}
	}

	private static void findUniqueCustomerIdForEachContractId(List<CustomerDTO> customerDTOs) {
		Map<Integer, List<Integer>> uniqueCustomerIdForEachContractId = new LinkedHashMap<>();

		for (CustomerDTO customerDTO : customerDTOs) {

			if (uniqueCustomerIdForEachContractId.containsKey(customerDTO.getContractId())) {
				List<Integer> list = uniqueCustomerIdForEachContractId.get(customerDTO.getContractId());
				if (!list.contains(customerDTO.getCustomertId())) {
					list.add(customerDTO.getCustomertId());
				}
				uniqueCustomerIdForEachContractId.put(customerDTO.getContractId(), list);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(customerDTO.getCustomertId());
				uniqueCustomerIdForEachContractId.put(customerDTO.getContractId(), list);
			}
		}

		uniqueCustomerIdForEachContractId.forEach((contactId, noOfCustomers) -> System.out
				.println("The number of unique customerId " + noOfCustomers.size() + " for contractId " + contactId));
	}

	private static Map<String, List<Integer>> findUniqueCustomerIdForEachGeozone(List<CustomerDTO> customerDTOs) {
		Map<String, List<Integer>> uniqueCustomerIdForEachGeozone = new LinkedHashMap<>();

		for (CustomerDTO customerDTO : customerDTOs) {

			if (uniqueCustomerIdForEachGeozone.containsKey(customerDTO.getGeoZone())) {
				List<Integer> list = uniqueCustomerIdForEachGeozone.get(customerDTO.getGeoZone());
				if (!list.contains(customerDTO.getCustomertId())) {
					list.add(customerDTO.getCustomertId());
				}
				uniqueCustomerIdForEachGeozone.put(customerDTO.getGeoZone(), list);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(customerDTO.getCustomertId());
				uniqueCustomerIdForEachGeozone.put(customerDTO.getGeoZone(), list);
			}
		}
		return uniqueCustomerIdForEachGeozone;
	}

	public static List<String> readInputData() {

		List<String> inputData = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);

		System.out.println("The data is organized into columns delimited by comma (,) in the following order: ");
		System.out.println("customerId,contractId,geozone,teamcode,projectcode,buildduration");

		while (scanner.hasNextLine()) {
			String read = scanner.nextLine();
			if (read == null || read.isEmpty()) { // if the line is empty
				break; // exit the loop
			}
			inputData.add(read);
		}
		
		scanner.close();

		return inputData;
	}

	public static List<CustomerDTO> convertListOfStringToListOfCustomerDTO(List<String> inputData) {

		return inputData.stream().map(OracleTechAssignment::convertToCustomerDTO).collect(Collectors.toList());
	}

	private static CustomerDTO convertToCustomerDTO(String input) {

		String[] split = input.split(",");
		CustomerDTO customerDTO = null;
		if (split.length == 6) {
			customerDTO = new CustomerDTO(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], split[3],
					split[4], Integer.parseInt(split[5].substring(0, split[5].length() - 1)));
		}

		return customerDTO;
	}
}
