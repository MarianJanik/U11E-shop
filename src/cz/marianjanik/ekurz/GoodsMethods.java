package cz.marianjanik.ekurz;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface GoodsMethods {
    /**
     * This method loads an item with given id @param id - id of the item which we want to load
     * @return
     *
     * Tato metoda načte položku s daným id @param id - id položky, kterou chceme načíst
     *
     */
    Item loadItemById(Integer id) throws SQLException;
    /**
     * This method deletes all items that are not in stock
     *
     * Tato metoda odstraní všechny položky, které nejsou na skladě
     */
    void deleteAllOutOfStockItems() throws SQLException;
    /**
     * This method loads all items that are in stock @return
     *
     * Tato metoda načte všechny položky, které jsou na skladě
     */
    List<Item> loadAllAvailableItems() throws SQLException;
    /**
     * This method saves the given item @param item - item to be saved
     *
     * Tato metoda uloží danou položku @param položku - položku, která má být uložena
     */
    void saveItem(Item item) throws SQLException;
    /**
     * This method updates a price of an item @param id - id of an item which price is to be updated
     * @param newPrice - new price
     *
     * Tato metoda aktualizuje cenu položky @param id - id položky, jejíž cenu je třeba aktualizovat
     */
    void updatePrice(Integer id, BigDecimal newPrice) throws SQLException;

}
