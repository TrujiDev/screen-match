//package com.trujidev.screenmatch;
//
//import com.trujidev.screenmatch.presentation.Menu;
//import com.trujidev.screenmatch.repository.SeriesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenMatchApplicationConsole implements CommandLineRunner {
//
//	@Autowired
//	SeriesRepository seriesRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenMatchApplicationConsole.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//		Menu menu = new Menu(seriesRepository);
//		menu.showMenu();
//	}
//}
