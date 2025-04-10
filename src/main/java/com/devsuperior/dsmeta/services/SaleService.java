package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleForSellerDTO;
import com.devsuperior.dsmeta.dto.SaleFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleForSellerDTO> findSalesSummaryByDate(LocalDate minDate, LocalDate maxDate) {
		return repository.findSalesSummaryByDate(minDate, maxDate);
	}

	public Page<SaleFullDTO> getSalesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable) {
		return repository.findSalesReportByDateAndName(minDate, maxDate, name, pageable);
	}
}
