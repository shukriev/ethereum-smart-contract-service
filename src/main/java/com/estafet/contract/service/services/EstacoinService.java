package com.estafet.contract.service.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import com.estafet.contract.service.contracts.Estacoin;

@Service
public class EstacoinService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EstacoinService.class);

    private static final BigInteger GAS_PRICE = BigInteger.valueOf(1L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(500_000L);
    
    
	@Autowired
	Web3j web3j;
	Credentials credentials = Credentials.create("731D5FBCC334AE85BA6BED3501D3353D6600B2BBA9995211C69793E9FDBE8DA9");

	String contractAddress = "0x68a3162c185934A0417f54E8C964E4b6Ee38F77d";

	private List<String> contracts = new ArrayList<>();
	
	public BigInteger balanceOf(String tokenOwner) throws Exception {
		Estacoin contract = Estacoin.load(contractAddress, web3j, credentials, new DefaultGasProvider());
		return contract.balanceOf(tokenOwner).send();
	}
	
	public boolean transfer(String to, BigInteger tokens) throws Exception {
		LOGGER.info("Transfer Coin From={}, following amount={} has been invoked", to, tokens);
		
		Estacoin contract = Estacoin.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);

		TransactionReceipt tr = contract.transfer(to, tokens).send();
		
		LOGGER.info("Transaction receipt: from={}, to={}, TX={}", tr.getFrom(), tr.getTo(), tr.getTransactionHash());

		return true;
	}
}
