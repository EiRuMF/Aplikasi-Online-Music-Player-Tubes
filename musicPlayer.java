import java.util.Scanner;

public class musicPlayer{


    
   static String music[][] = new String [1000][4];
   String playlist[] = new String [1000];
   static int indexMusic =0;

   static void tambahMusic(String judul,String artis,String durasi,String album){
    
    
    music[indexMusic][0]=judul;
    music[indexMusic][1]=artis;
    music[indexMusic][2]=durasi;
    music[indexMusic][3]=album;
    indexMusic++;
    
  }

  static void tampilkanMusic(){
    if(indexMusic==0){
      System.out.println("Belum ada musik");
    }else{
      for(int i=0;i<indexMusic;i++){
        System.out.println(music[i][0]+"|"+music[i][1]+"|"+music[i][2]+"|"+music[i][3]);
      }
    }
  }

  static void editDataMusic(String tambahMusic){

  }

  static void hapusDataMusic(String tambahMusic){

  }

  // Bagian Playlist

  static void tambahPlaylist(String music){
      if (banyakPlaylist <= 10){
        System.out.println("Playlist Penuh");
        return;
      } else {
        System.out.println("Masukkan nama playlist: ");
        String addNamaPlaylist = input.nextString();

        namaPlaylist[banyakPlaylist] = nama;
        banyakPlaylist

        banyakPlaylist++;
      }
  }

  static void tampilkanPlaylist(String tambahPlaylist){

  }

  static void editDataPlaylist(String tambahPlaylist){

  }

  static void hapusDataPlaylist(String tambahPlaylist){

  }

  static void cariData(String tambahMusic){

  }

  static void play(String music){

  }

  static void shufflePlay(String rekomendasi){

  }

  static void rekomendasiMusic(String tampilkanPlaylist){
    
  }
  
  

  

  

  

  

  

  public static void main(String [] args){
     

      int pilihan = 0;

      do{
        System.out.println("==ONLINE MUSIC PLAYER==");
        System.out.println("1. Lihat semua musik");
        System.out.println("2. Cari musik");
        System.out.println("3. Tambah musik");
        System.out.println("4. Hapus musik");
        pilihan = in.nextInt();

        if(pilihan == 1){
          musicList(music);
        }
        
      }
      while (pilihan != 0); {
        System.out.println("Keluar dari aplikasi");
      }

     
    
      


  }
}