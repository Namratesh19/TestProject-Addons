package io.testproject.myaddon;
import io.testproject.java.annotations.v2.Action; 
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

import java.util.ArrayList;
import java.util.Collections;

@Action(name = "Validate Column Sort", description = "This addon takes a table, number of columns, column number, and order as the input. It then checks whether that column is sorted in the required order.")
public class TableSortAction implements WebAction {
	@Parameter
	public String table; // entire table stored as string
	@Parameter
	public int no_of_column; // number of columns in table
	@Parameter
	public int column_no; //  column no to be extracted and checked
	@Parameter
	public String order; // Whether user want to check ascending or descending order
    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {
        String[] table_array = table.split("[,]", 0); // storing table read as CSV in a string array
        ArrayList<String> original_column = new ArrayList<String>(); // storing desired column  
        for(int k = column_no-1;k < table_array.length;k = k+no_of_column){
            original_column.add(table_array[k]);
        }
        ArrayList<String> column  = new ArrayList<String>(original_column);
        if(order.compareToIgnoreCase("Ascending") == 0) Collections.sort(original_column);
        if(order.compareToIgnoreCase("Descending") == 0) Collections.sort(original_column, Collections.reverseOrder());
        if (column.equals(original_column)) return ExecutionResult.PASSED;
        else return ExecutionResult.FAILED; 
    }
}



