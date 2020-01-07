package com.estafet.contract.service.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estafet.contract.service.model.EstacoinBuyTransaction;
import com.estafet.contract.service.services.EstacoinService;

@RestController
@RequestMapping("/estacoin")
public class EstacoinController {

	@Autowired
	private EstacoinService estaCoinService;
	
	@GetMapping("/balance/{address}")
	public BigInteger getOwnerAccount(@PathVariable String address) throws Exception {
		return estaCoinService.balanceOf(address);
	}

	@PostMapping("/transfer-coin")
	public boolean transferCoin(@RequestBody EstacoinBuyTransaction estaCoinTransactionRequest) throws Exception {
		return estaCoinService.transfer(estaCoinTransactionRequest.getAddress(), estaCoinTransactionRequest.getAmount());
	}
}
