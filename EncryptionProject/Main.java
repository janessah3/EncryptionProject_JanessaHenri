class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   
    // Array1: Vowel characters
    char[] sub = new char[2];
    sub[0] = 'a';
    sub[1] = 'e';
    
    // Array2: Unicode characters
    char[] sub2 = new char[2];
    sub2[0] = '\u2660';  // Spade
    sub2[1] = '\u2665';  // Heart
  

    // Encoding the plaintext:
    String file = Input.readFile("Original.txt");
    
    // Encode level 1 - Substitution
    String encodedMsg1 = subEncryption(file, sub, sub2);
    Input.writeFile("Encode1.txt", encodedMsg1);
    
    // // Encode level 2 - Caesar Cipher (Shifting by 3)
    String encodedMsg2 = caesarEncryption(encodedMsg1, 3);
    Input.writeFile("Encode2.txt", encodedMsg2);
   
    // // Encode level 3 - Fillers 
    String encodedMsg3 = addFillers(encodedMsg2);
    encodedMsg3 = reverse(encodedMsg3);
    Input.writeFile("Encode3.txt", encodedMsg3);

   
    // Decoding the ciphertext:
    String file2 = Input.readFile("Encode3.txt");
   
    // Decode level 3  (reverse + remove filler )
    String decodedMsg1 = reverse(file2);
    decodedMsg1 = removeFillers(decodedMsg1);
    Input.writeFile("Decode1.txt", decodedMsg1);

    // Decode level 2 (remove caesar cipher)
    String decodedMsg2 = caesarDecryption(decodedMsg1, 3);
    Input.writeFile("Decode2.txt", decodedMsg2);
  
    // Decode level 1 (reverse substitution)
    String decodedMsg3 = subEncryption(decodedMsg2, sub2, sub);
    Input.writeFile("Decode3.txt", decodedMsg3);
  }

    // caesar encyrpt
    String caesarEncryption(String txt, int shift) {
      String build = "";
      for (int x = 0; x < txt.length(); x++) {
          char ch = txt.charAt(x);
          build += (char)(ch + shift);
    }
    return build;
}

  // caesar decrypt
  String caesarDecryption(String txt, int shift) {
    String build = "";
    for (int x = 0; x < txt.length(); x++) {
        char ch = txt.charAt(x);
        build += (char)(ch - shift);
    }
    return build;
}
    
    // reverse a String
    String reverse(String txt){
      String build ="";
      for(int x=0; x< txt.length(); x++){
        build = txt.charAt(x) + build;
    }
    return build;
  }
 
 
  // Cipher +3 encoding with no wrapping
  String encode(String txt){
    String build = "";
    for(int x = 0; x < txt.length(); x++){
      build += (char)(txt.charAt(x) + 3);
    }
    return build;
  }


  // Cipher -3 encoding with no wrapping
  String decode(String txt){
    String build = "";
    for(int x = 0; x < txt.length(); x++){
      build += (char)(txt.charAt(x) - 3);
    }
    return build;
  }

  // Adding fillers
  String addFillers(String txt){
    String build = "";
    for(int x = 0; x < txt.length(); x++){
      build += txt.charAt(x);
      build += '#';
    }
    return build;
  }

  // Removing fillers
  String removeFillers(String txt){
    return txt.replace("#", "");
  }


  // Substitution decoding
  String subEncryption(String s, char[] sub, char[] sub2){
    String build = "";
    char ch ='\0';
    int index=0;
   
    for(int x=0; x<s.length(); x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }


  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }


  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }


}
