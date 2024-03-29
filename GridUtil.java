import java.io.*;
import java.util.*;

public class GridUtil{    
    private static File file;
    private BufferedReader br;
    private GridValidators gv = new GridValidators();
    
    public GridUtil(){
    }
    
    public void setFile(String fileName){
        file = new File(fileName);
    }
    
    public boolean readFile(){
        try{
            br = new BufferedReader(new FileReader(file));
            return true;
        } catch(FileNotFoundException e){
            System.out.println("File not found!");
            return false;
        } 
    }
    
    public ArrayList<LinkedHashMap<GridObject,String>> populateCollection(){
        readFile();
        ArrayList<LinkedHashMap<GridObject,String>> alist = 
            new ArrayList<LinkedHashMap<GridObject,String>>();
        try {
            String tempString = "";
            while((tempString = br.readLine()) != null){
                LinkedHashMap<GridObject,String> accessList = 
                    new LinkedHashMap<GridObject,String>();
                for(String temp : tempString.split(" ")){
                    String[] splitTemp = temp.split("\\,");
                    accessList.put(new GridObject(splitTemp[0]),(splitTemp[1]));
                }
                alist.add(accessList);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return alist;
    }
    
    public void writeToFile(String content){    
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
