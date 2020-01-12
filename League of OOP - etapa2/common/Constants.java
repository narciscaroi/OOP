package common;

public final class Constants {
      public static final int MAXROUNDS = 30;

      public static final int PLAYERBASEUPXP = 250;
      public static final int PLAYERXPMULTIPLIER = 50;
      public static final int MINEXPGET = 0;
      public static final int MAXFCTARGEXP = 200;
      public static final int PLAYERMAXFCTMULT = 40;

      //pyro constante
      public static final int PYROHP = 500;
      public static final int PYRDMG = 350;
      public static final int PYRIGNDMG = 150;
      public static final int PYRDMGPERLVL = 50;
      public static final int PYRIGNDMGPERLVL = 20;
      public static final int PYRIGNDOTDMG = 50;
      public static final int PYRIGNDOTPERLVL = 30;
      public static final int PYROHPPERLVL = 50;
      public static final float PYRFIELDBONUS = 1.25f;
      public static final int PYRIGNDOTTIME = 2;

      //fireblast modificatori
      public static final float PYRDMGPTR = 0.8f;
      public static final float PYRDMGPTK = 1.20f;
      public static final float PYRDMGPTP = 0.90f;
      public static final float PYRDMGPTW = 1.05f;
      //pyro ignite modificatori
      public static final float PYRIGNPTR = 0.80f;
      public static final float PYRIGNPTK = 1.20f;
      public static final float PYRIGNPTP = 0.90f;
      public static final float PYRIGNPTW = 1.05f;

      //rogue constants
      public static final int ROGCRITCH = 3;
      public static final float ROGCRITPROC = 1.50f;
      public static final int ROGHPPERLVL = 40;
      public static final int ROGHP = 600;
      public static final int ROGDMG = 200;
      public static final int ROGDMGPERLVL = 20;
      public static final float ROGFIELDBONUS = 1.15f;

      //rogue backstab constante
      public static final float BSMODRTR = 1.2f;
      public static final float BSMODRTK = 0.9f;
      public static final float BSMODRTP = 1.250f;
      public static final float BSMODRTW = 1.250f;
      public static final int ROGVERIFYIFCRIT = 3;

      //rogue paralysis constante
      public static final float PMODRTR = 0.900f;
      public static final float PMODRTK = 0.800f;
      public static final float PMODRTP = 1.200f;
      public static final float PMODRTW = 1.250f;
      public static final int PARALYDMG = 40;
      public static final int PARALYDMGPERLVL = 10;
      public static final int PDOTWTIME = 6; //
      public static final int PDOTTIME = 3; // 3 --///--

      //wizard constante
      public static final int WIZHP = 400;
      public static final float WIZPROC = 0.2f;
      public static final int WIZHPPERLVL = 30;
      public static final float WIZDEFLPR = 0.350f;
      public static final float WIZDELFINCPROC = 0.020f;
      public static final float WIZDEFLMAXPROC = 0.700f;
      public static final float WIZPROCPERLVL = 0.050f;
      public static final float WIZDESERTBONUS = 1.1f;
      public static final float WIZMINFCTMIN = 0.30f;

      //wizard drain modifiatori
      public static final float DRMODWTW = 0.05f;
      public static final float DRMODWTR = 0.2f;
      public static final float DRMODWTP = 0.1f;
      public static final float DRMODWTK = 0.200f;
      //wizard deflect modificators
      public static final float DEFKODWTR = 1.200f;
      public static final float DEFKODWTK = 1.400f;
      public static final float DEFKODWTP = 1.3f;


      //knight constante
      public static final int KNIGHTHP = 900;
      public static final int KNIGHTDMG = 200;
      public static final int KNIGHDMGPERLVL = 30;
      public static final int KNIGHTHPPERLVL = 80;
      public static final float KNIGHTFIELDBONUS = 1.15f;

      //dmg modificatori
      public static final float KMODKTR = 1.150f;
      public static final float KMODKTK = 1;
      public static final float KMODKTP = 1.100f;
      public static final float KMODKTW = 0.80f;
      public static final float KENEMYHPPROC = 0.200f;
      public static final float KENEMYMINPROC = 0.01f;
      public static final float KENEMYMAXPROC = 0.400f;

      //slam modificatori
      public static final int KSLAMBASEDMG = 100;
      public static final int KSLAMDMGPERLVL = 40;
      public static final float KMODSLAMKTR = 0.80f;
      public static final float KMODSLAMKTK = 1.20f;
      public static final float KMODSLAMKTP = 0.90f;
      public static final float KMODSLAMKTW = 1.05f;

      //Strategy-knight
      public static final float KLEFTFRAC = 1 / 4f;
      public static final float KRIGHTFRAC = 1 / 2f;
      public static final float KUPCOEF = 0.5f;
      public static final float KDOWNHP = 4 / 5f;
      public static final float KDOWNCOEF = 0.2f;
      public static final float KUPHP = 5 / 4f;

      //pyromancer
      public static final float PLEFTFRAC = 1 / 4f;
      public static final float PRIGHTFRAC = 1 / 3f;
      public static final float PDOWNHP = 3 / 4f;
      public static final float PUPCOEF = 0.7f;
      public static final float PDOWNCOEF = 0.3f;
      public static final float PUPHP = 4 / 3f;

      //Strategy-rogue
      public static final float RLEFTFRAC = 1 / 7f;
      public static final float RRIGHTFRAC = 1 / 5f;
      public static final float RDOWNHP = 6 / 7f;
      public static final float RUPCOEF = 0.4f;
      public static final float RDOWNCOEF = 0.1f;
      public static final float RUPHP = 1.5f;
      //Strategy-wizard
      public static final float WLEFTFRAC = 1 / 4f;
      public static final float WRIGHTFRAC = 1 / 2f;
      public static final float WDOWNHP = 9 / 10f;
      public static final float WUPCOEF = 0.6f;
      public static final float WDOWNCOEF = 0.2f;
      public static final float WUPHP = 6 / 5f;

      //Angels
      //DamageAngel
      public static final float DAK = 0.15f;
      public static final float DAP = 0.2f;
      public static final float DAR = 0.3f;
      public static final float DAW = 0.4f;
      //DarkAngel
      public static final float DALK = 40;
      public static final float DALP = 30;
      public static final float DALR = 20;
      public static final float DALW = 10;
      //Dracula
      public static final float DDK = 0.2f;
      public static final float DHK = 60;
      public static final float DDP = 0.3f;
      public static final float DHP = 40;
      public static final float DDR = 0.1f;
      public static final float DHR = 35;
      public static final float DDW = 0.4f;
      public static final float DHW = 20;
      //GoodBoy
      public static final float GBDK = 0.4f;
      public static final float GBHK = 20;
      public static final float GBDP = 0.5f;
      public static final float GBHP = 30;
      public static final float GBDR = 0.4f;
      public static final float GBHR = 40;
      public static final float GBDW = 0.3f;
      public static final float GBHW = 50;
      //LevelUpAngel
      public static final float LUAKMOD = 0.1f;
      public static final float LUAPMOD = 0.2f;
      public static final float LUARMOD = 0.15f;
      public static final float LUAWMOD = 0.25f;
      //LifeGiver
      public static final float LGK = 100;
      public static final float LGP = 80;
      public static final float LGR = 90;
      public static final float LGW = 120;

      //SmallAngel
      public static final float SADK = 0.1f;
      public static final float SAHK = 10;
      public static final float SADP = 0.15f;
      public static final float SAHP = 15;
      public static final float SADR = 0.05f;
      public static final float SAHR = 20;
      public static final float SADW = 0.1f;
      public static final float SAHW = 25;
      //Spawner
      public static final float SHK = 200;
      public static final float SHP = 150;
      public static final float SHR = 180;
      public static final float SHW = 120;
      //TheDoomer

      //XPAngel
      public static final int XPAK = 45;
      public static final int XPAP = 50;
      public static final int XPAR = 40;
      public static final int XPAW = 60;

      //what Angel does
      public static final String GOODANGSTR = "helped";
      public static final String BADANGSTR = "hit";
      private Constants() {
            //just to trock checkstyle
      }

}
