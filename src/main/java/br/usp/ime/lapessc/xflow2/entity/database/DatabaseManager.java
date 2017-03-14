
/* 
 * 
 * XFlow
 * _______
 * 
 *  
 *  (C) Copyright 2010, by Universidade Federal do Pará (UFPA), Francisco Santana, Jean Costa, Pedro Treccani and Cleidson de Souza.
 * 
 *  This file is part of XFlow.
 *
 *  XFlow is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  XFlow is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XFlow.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *  ====================
 *  DatabaseManager.java
 *  ====================
 *  
 *  Original Author: Francisco Santana;
 *  Contributor(s):  Pedro Treccani, David Bentolila;
 *  
 */

package br.usp.ime.lapessc.xflow2.entity.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.usp.ime.lapessc.xflow2.exception.persistence.DatabaseException;

public class DatabaseManager {

	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("xflow-persistence-unit");

	public static EntityManager getDatabaseSession() throws DatabaseException {
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
}