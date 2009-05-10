/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package junit.samples.money;

/**
 * The common interface for simple Monies and MoneyBags
 *
 */
public interface IMoney {
	/**
	 * Adds a money to this money.
	 */
	public abstract IMoney add(IMoney m);
	/**
	 * Adds a simple Money to this money. This is a helper method for
	 * implementing double dispatch
	 */
	public abstract IMoney addMoney(Money m);
	/**
	 * Adds a MoneyBag to this money. This is a helper method for
	 * implementing double dispatch
	 */
	public abstract IMoney addMoneyBag(MoneyBag s);
	/**
	 * Tests whether this money is zero
	 */
	public abstract boolean isZero();
	/**
	 * Multiplies a money by the given factor.
	 */
	public abstract IMoney multiply(int factor);
	/**
	 * Negates this money.
	 */
	public abstract IMoney negate();
	/**
	 * Subtracts a money from this money.
	 */
	public abstract IMoney subtract(IMoney m);
	/**
	 * Append this to a MoneyBag m.
	 */
	public abstract void appendTo(MoneyBag m);
}