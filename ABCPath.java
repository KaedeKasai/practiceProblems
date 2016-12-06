package practiceProblems;

public class ABCPath {
	
	public static String[] chr ={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "END"};
	
	public static void main(String[] args){
		String[] str = {"AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" };
		
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
		System.out.println("ans is " + count);
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
				int[] crrent = {depth, width};
				int crrentDepth = depth;
				int crrentWidth = width;
				
				System.out.println();
				System.out.print("#####[" + depth + ", ");
				System.out.println(width + "]#####");
				
				while(true){
					

					
					if(!strArray[crrentDepth][crrentWidth].equals(chr[chrIndex])){
						
						break;
						
					}
					System.out.println();
					System.out.println("D= " + crrentDepth);
					System.out.println("W= " + crrentWidth);
					System.out.println();
					System.out.println(strArray[crrentDepth][crrentWidth]);
					System.out.println(chr[chrIndex]);
					boolean ans = ((strArray[crrentDepth][crrentWidth]).equals(chr[chrIndex]));
					System.out.println(ans);
					System.out.println("--------[" + (chrIndex + 1) + "]--------");
					
					chrIndex++;
					
					if(crrentDepth != 0 & crrentDepth != depthLength){
						
						if(crrentWidth != 0 & crrentWidth != widthLength){
							
							crrent = NormalC(strArray, crrent, chrIndex);
							
						}else if(crrentWidth == 0){
							
							crrent = LeftSideC(strArray, crrent, chrIndex);
							
						}else if(crrentWidth == widthLength){
							
							crrent = RightSideC(strArray, crrent, chrIndex);
							
						}
						
					}else if(crrentDepth == 0){
						
						if(crrentWidth != 0 & crrentWidth != widthLength){
							
							crrent = UpSideC(strArray, crrent, chrIndex);
							
						}else if(crrentWidth == 0){
							
							crrent = UpLeftC(strArray, crrent, chrIndex);
							
						}else if(crrentWidth == widthLength){
							
							crrent = UpRightC(strArray, crrent, chrIndex);
							
						}
						
					}else if(crrentDepth == depthLength){
						
						if(crrentWidth != 0 & crrentWidth != widthLength){
							
							crrent = UnderSideC(strArray, crrent, chrIndex);
							
						}else if(crrentWidth == 0){
							
							crrent = UnderLeftC(strArray, crrent, chrIndex);;
							
						}else if(crrentWidth == widthLength){
							
							crrent = UnderRightC(strArray, crrent, chrIndex);
							
						}
					}
					
					if(count < chrIndex){
						count = chrIndex;
					}
					crrentDepth = crrent[0];
					crrentWidth = crrent[1];
				}
				
				
			}
		}
		return count;
	}
	
	static int[] NormalC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("NormalC");

		
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UpSideC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UpSide");
		
		for(int i = 0; i < 2; i++){
			for(int j = -1; j < 2; j++){
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] LeftSideC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("ok");
		System.out.println("LeftSide");
		
		for(int i = -1; i < 2; i++){
			for(int j = 0; j < 2; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] RightSideC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("RightSide");
		
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 1; j++){
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UnderSideC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UnderSide");
		
		for(int i = -1; i < 1; i++){
			for(int j = -1; j < 2; j++){
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UpLeftC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UpLeft");
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UpRightC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UpRight");
		
		for(int i = 0; i < 2; i++){
			for(int j = -1; j < 1; j++){
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
				if(i == 0 & j == -1){
					j++;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UnderLeftC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UnderLeft");
		
		for(int i = -1; i < 1; i++){
			for(int j = 0; j < 2; j++){
				
				if(i == 0 & j == 0){
					j++;
				}
				
				if(strArray[ crrent[0] + i ][ crrent[1] + j ].equals(chr[chrIndex])){
					nextDepth = i;
					nextWidth = j;
				}
			}
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
	static int[] UnderRightC(String[][]strArray, int[] crrent, int chrIndex){
		
		int nextDepth = 0;
		int nextWidth = 0;
		
		System.out.println("UnderRight");
		int depth = -1;
		int width = -1;
		
		if(strArray[ crrent[0] + depth][ crrent[1] + width ].equals(chr[chrIndex])){
			nextDepth = depth;
			nextWidth = width;
		}
		depth++;
		if(strArray[ crrent[0] + depth][ crrent[1] + width ].equals(chr[chrIndex])){
			nextDepth = depth;
			nextWidth = width;
		}
		depth--;
		width++;
		if(strArray[ crrent[0] + depth][ crrent[1] + width ].equals(chr[chrIndex])){
			nextDepth = depth;
			nextWidth = width;
		}
		crrent[0] += nextDepth;
		crrent[1] += nextWidth;
		
		return crrent;
	}
}
