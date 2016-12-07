package practiceProblems;

import java.util.Scanner;

public class ABCPath {
	
	private final static String[] chr ={
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "END"};
	private final static int rangeTopOfDepth = -1;
	private final static int rangeTopOfWidth = -1;
	private final static int rangeEndDepth = 1;
	private final static int rangeEndWidth = 1;
	

	
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		String deta;
		
		System.out.println("二次配列データ入力");
		deta = scan.next();
		scan.close();
		
		System.out.println(deta);
		
		String[] str = {deta};
		
		int depthLength = str.length - 1;
		int widthLength = str[0].length() - 1;
		
		String[][] strArray = DoubleArray(str);
		
		for (int depth = 0; depth <= depthLength; depth++) {
			for (int width = 0; width <= widthLength ; width++){
				System.out.print(strArray[depth][width] + " ");
			}
			System.out.println();
		}
		
		int count = Comparison(depthLength, widthLength, strArray);
		
		System.out.println();
		System.out.println("Returns:" + count);
		if(count == 0){
			System.out.println("Paths must start with an 'A'.");
		}
	}
	
	static String[][] DoubleArray(String[] str){
		
		String[][] strArray;
		strArray = new String[(str.length)][];
		
		for(int i = 0;i < str.length;i++){
			strArray[i] = new String [str[i].length()];
		}
		
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str[i].length(); j++) {
				strArray[i][j] = String.valueOf(str[i].charAt(j));
			}
		}
		return strArray;
	}
	
	static int Comparison(int depthLength, int widthLength, String[][] strArray){
		
		int count = 0;
		int chrIndex = 0;
		
		for (int depth = 0; depth <= depthLength; depth++) {
			for (int width = 0; width <= widthLength ; width++){
				
				chrIndex = 0;
				int[] current = {depth, width};
				int currentDepth = depth;
				int currentWidth = width;
				
				System.out.println();
				System.out.print("#####[" + depth + ", " + width + "]#####");
				
				while(true){
					

					
					if(!strArray[currentDepth][currentWidth].equals(chr[chrIndex])){
						
						break;
						
					}
					System.out.println();
					System.out.println("D= " + currentDepth);
					System.out.println("W= " + currentWidth);
					System.out.println();
					System.out.println(strArray[currentDepth][currentWidth]);
					System.out.println(chr[chrIndex]);
					boolean ans = ((strArray[currentDepth][currentWidth]).equals(chr[chrIndex]));
					System.out.println(ans);
					System.out.println("--------[" + (chrIndex + 1) + "]--------");
					
					chrIndex++;
					
					boolean notTopOrTailToDepth = currentDepth != 0 & currentDepth != depthLength;
					boolean topToDepth          = currentDepth == 0;
					boolean tailToDepth         = currentDepth == depthLength;
					boolean notTopOrTailToWidth = currentWidth != 0 & currentWidth != widthLength;
					boolean topToWidth          = currentWidth == 0;
					boolean tailToWidth         = currentWidth == widthLength;
					
					if(notTopOrTailToDepth){
						
						if(notTopOrTailToWidth){
							
							current = NormalC(strArray, current, chrIndex);
							
						}else if(topToWidth){
							
							current = LeftSideC(strArray, current, chrIndex);
							
						}else if(tailToWidth){
							
							current = RightSideC(strArray, current, chrIndex);
							
						}
						
					}else if(topToDepth){
						
						if(notTopOrTailToWidth){
							
							current = UpSideC(strArray, current, chrIndex);
							
						}else if(topToWidth){
							
							current = UpLeftC(strArray, current, chrIndex);
							
						}else if(tailToWidth){
							
							current = UpRightC(strArray, current, chrIndex);
							
						}
						
					}else if(tailToDepth){
						
						if(notTopOrTailToWidth){
							
							current = UnderSideC(strArray, current, chrIndex);
							
						}else if(topToWidth){
							
							current = UnderLeftC(strArray, current, chrIndex);;
							
						}else if(tailToWidth){
							
							current = UnderRightC(strArray, current, chrIndex);
							
						}
					}
					
					if(count < chrIndex){
						count = chrIndex;
					}
					currentDepth = current[0];
					currentWidth = current[1];
				}
			}
		}
		return count;
	}
	
	static int[] NormalC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("NormalC");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth; j <= rangeEndWidth; j++){
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UpSideC(String[][]strArray, int[] current, int chrIndex){
		
//		System.out.println("UpSide");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth +1; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth; j <= rangeEndWidth; j++){
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] LeftSideC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("LeftSide");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth +1; j <= rangeEndWidth; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] RightSideC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("RightSide");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth; j < rangeEndWidth; j++){
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UnderSideC(String[][]strArray, int[] current, int chrIndex){

//		System.out.println("UnderSide");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth; i < rangeEndDepth; i++){
			for(int j = rangeTopOfWidth; j <= rangeEndWidth; j++){
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UpLeftC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("UpLeft");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth + 1; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth + 1; j <= rangeEndWidth; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UpRightC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("UpRight");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth + 1; i <= rangeEndDepth; i++){
			for(int j = rangeTopOfWidth; j < rangeEndWidth; j++){
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UnderLeftC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("UnderLeft");
		int nextDepth = 0;
		int nextWidth = 0;
		
		for(int i = rangeTopOfDepth; i < rangeEndDepth; i++){
			for(int j = rangeTopOfWidth + 1; j <= rangeEndWidth; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ current[0] + i ][ current[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
	
	static int[] UnderRightC(String[][]strArray, int[] current, int chrIndex){
		

//		System.out.println("UnderRight");
		int nextDepth = 0;
		int nextWidth = 0;
		int depth = rangeTopOfDepth;
		int width = rangeTopOfWidth;
		boolean currentIndexEqualsChrIndex 
		= strArray[ current[0] + depth][ current[1] + width ].equals(chr[chrIndex]);
		
		if(currentIndexEqualsChrIndex){
			nextDepth = depth;
			nextWidth = width;
		}
		depth++;
		if(currentIndexEqualsChrIndex){
			nextDepth = depth;
			nextWidth = width;
		}
		depth--;
		width++;
		if(currentIndexEqualsChrIndex){
			nextDepth = depth;
			nextWidth = width;
		}
		current[0] += nextDepth;
		current[1] += nextWidth;
		
		return current;
	}
}
