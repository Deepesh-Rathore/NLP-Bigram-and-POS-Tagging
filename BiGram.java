import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BiGram {
	
	
	static class BigramKey{
		String first;
		String second;
		
		public boolean equals(Object object) {
	        if (!(object instanceof BigramKey)) {
	            return false;
	        }

	        BigramKey otherKey = (BigramKey) object;
	        return this.first.equals(otherKey.first) && this.second.equals(otherKey.second);
	    }

	    @Override
	    public int hashCode() {
	        int result = 17; // any prime number
	        result = 31 * result + this.first.hashCode();
	        result = 31 * result + this.second.hashCode();
	        return result;
	    }
	}

	private HashMap<BigramKey, Integer> bigramCount = new HashMap<>();
	private HashMap<String, Integer> unigramCount = new HashMap<>();
	private HashMap<BigramKey, Double> bigramProbability = new HashMap<>();
	
	public void calcunigramcounts(String text) {
		
		String[] textArray = text.split("\\s+");
		
		for(String s : textArray)
		{
			if(unigramCount.containsKey(s))
			{
				unigramCount.put(s, unigramCount.get(s)+1);
			}
			else
			{
				unigramCount.put(s, 1);
			}
		}
		
		System.out.println("unigramCount "+unigramCount.get("for"));
	}
	
	public void calcbigramcounts(String text) {
		
		String[] textArray = text.split("\\s+");
		
		for(int i=1; i<textArray.length;i++)
		{
			BigramKey key = new BigramKey();
			key.first = textArray[i-1];
			key.second = textArray[i];
			
			if(bigramCount.containsKey(key))
			{
				bigramCount.put(key, bigramCount.get(key)+1);
				bigramProbability.put(key, 0.0);
			}
			else
			{
				bigramCount.put(key, 1);
				bigramProbability.put(key, 0.0);
			}
		}
		
		BigramKey key1 = new BigramKey();
		key1.first="for";
		key1.second="the";
		System.out.println("bigramCount "+bigramCount.get(key1));
		
	}
	
	public void calcbigramProbability() {
		
		for(BigramKey k : bigramCount.keySet())
		{
			bigramProbability.put(k, (double)bigramCount.get(k)/(double)unigramCount.get(k.first));
		}
		
		BigramKey key1 = new BigramKey();
		key1.first="for";
		key1.second="the";
		System.out.println("bigramProbability "+bigramProbability.get(key1));
	}
	
	public void calctotalwords() {
		
		
		System.out.println("count "+unigramCount.size());
	}
	
	public void writetofile() throws IOException {

		String unigramcount = "C:\\Users\\VisualBI\\Desktop\\temp\\nlp hw2\\unigramcount.txt";
		String bigramcount = "C:\\Users\\VisualBI\\Desktop\\temp\\nlp hw2\\bigramcount.txt";
		String bigramprobability = "C:\\Users\\VisualBI\\Desktop\\temp\\nlp hw2\\bigramprobability.txt";

		BufferedWriter writeruc = new BufferedWriter(new FileWriter(unigramcount));
		BufferedWriter writerbc = new BufferedWriter(new FileWriter(bigramcount));
		BufferedWriter writerbp = new BufferedWriter(new FileWriter(bigramprobability));

		for(String k : unigramCount.keySet())
		{
			
			writeruc.write(k+" : "+unigramCount.get(k)+"\n");
		}
		writeruc.close();

	    for(BigramKey k : bigramCount.keySet())
		{
			
			writerbc.write(k.first+" !:! "+k.second+" : "+bigramCount.get(k)+"\n");
		}
	    writerbc.close();

	    for(BigramKey k : bigramProbability.keySet())
		{
			writerbp.write(k.first+" !:! "+k.second+" : "+bigramProbability.get(k)+"\n");
		}
	    writerbp.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileName = "C:\\Users\\VisualBI\\Desktop\\temp\\HW2_S18_NLP6320-NLPCorpusTreebank2Parts-CorpusA-Windows.txt";
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null){
		     //process the line
			sb.append(line);
			sb.append(" ");
		}
		br.close();

		String all_lines = new String(sb);

		BiGram b = new BiGram();
		b.calcunigramcounts(all_lines);
		b.calcbigramcounts(all_lines);
		b.calcbigramProbability();
		b.calctotalwords();
		b.writetofile();

//		System.out.println(all_lines);
//		fileName = "C:\\Users\\VisualBI\\Desktop\\temp\\HW2_full.txt";
//		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//	    writer.write(all_lines);
//	    writer.close();

	}

}
