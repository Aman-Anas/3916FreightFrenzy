
namespace FreightFrenzy_Calculator
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.grpAuto = new System.Windows.Forms.GroupBox();
            this.grpCV = new System.Windows.Forms.GroupBox();
            this.radNoCV = new System.Windows.Forms.RadioButton();
            this.radCVDuck = new System.Windows.Forms.RadioButton();
            this.radCVThing = new System.Windows.Forms.RadioButton();
            this.grpAFreight = new System.Windows.Forms.GroupBox();
            this.radAFreightSU = new System.Windows.Forms.RadioButton();
            this.radAFreightSH = new System.Windows.Forms.RadioButton();
            this.grpAParked = new System.Windows.Forms.GroupBox();
            this.radAPartSU = new System.Windows.Forms.RadioButton();
            this.radACompSU = new System.Windows.Forms.RadioButton();
            this.radAPartWH = new System.Windows.Forms.RadioButton();
            this.radACompWH = new System.Windows.Forms.RadioButton();
            this.chkADuck = new System.Windows.Forms.CheckBox();
            this.grpTeleOp = new System.Windows.Forms.GroupBox();
            this.lblTOSharedHub = new System.Windows.Forms.Label();
            this.lblTOShipHub3 = new System.Windows.Forms.Label();
            this.lblTOShipHub2 = new System.Windows.Forms.Label();
            this.lblTOShipHub1 = new System.Windows.Forms.Label();
            this.lblTOStorageUnit = new System.Windows.Forms.Label();
            this.numTOSharedHub = new System.Windows.Forms.NumericUpDown();
            this.numTOStorageUnit = new System.Windows.Forms.NumericUpDown();
            this.numTOShipHub3 = new System.Windows.Forms.NumericUpDown();
            this.numTOShipHub2 = new System.Windows.Forms.NumericUpDown();
            this.numTOShipHub1 = new System.Windows.Forms.NumericUpDown();
            this.grpEndgame = new System.Windows.Forms.GroupBox();
            this.numEDucks = new System.Windows.Forms.NumericUpDown();
            this.lblEDucks = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.radEPWH = new System.Windows.Forms.RadioButton();
            this.radECWH = new System.Windows.Forms.RadioButton();
            this.chkESHCap = new System.Windows.Forms.CheckBox();
            this.chkESHBal = new System.Windows.Forms.CheckBox();
            this.chkEAHBal = new System.Windows.Forms.CheckBox();
            this.lblAPoints = new System.Windows.Forms.Label();
            this.lblTOPoints = new System.Windows.Forms.Label();
            this.lblEPoints = new System.Windows.Forms.Label();
            this.lblPoints = new System.Windows.Forms.Label();
            this.lblTotPoints = new System.Windows.Forms.Label();
            this.grpAuto.SuspendLayout();
            this.grpCV.SuspendLayout();
            this.grpAFreight.SuspendLayout();
            this.grpAParked.SuspendLayout();
            this.grpTeleOp.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numTOSharedHub)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOStorageUnit)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub1)).BeginInit();
            this.grpEndgame.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numEDucks)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // grpAuto
            // 
            this.grpAuto.Controls.Add(this.grpCV);
            this.grpAuto.Controls.Add(this.grpAFreight);
            this.grpAuto.Controls.Add(this.grpAParked);
            this.grpAuto.Controls.Add(this.chkADuck);
            this.grpAuto.Location = new System.Drawing.Point(21, 12);
            this.grpAuto.Name = "grpAuto";
            this.grpAuto.Size = new System.Drawing.Size(171, 348);
            this.grpAuto.TabIndex = 6;
            this.grpAuto.TabStop = false;
            this.grpAuto.Text = "Autonomous";
            // 
            // grpCV
            // 
            this.grpCV.Controls.Add(this.radNoCV);
            this.grpCV.Controls.Add(this.radCVDuck);
            this.grpCV.Controls.Add(this.radCVThing);
            this.grpCV.Location = new System.Drawing.Point(9, 251);
            this.grpCV.Name = "grpCV";
            this.grpCV.Size = new System.Drawing.Size(152, 91);
            this.grpCV.TabIndex = 12;
            this.grpCV.TabStop = false;
            this.grpCV.Visible = false;
            // 
            // radNoCV
            // 
            this.radNoCV.AutoSize = true;
            this.radNoCV.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radNoCV.Location = new System.Drawing.Point(11, 8);
            this.radNoCV.Name = "radNoCV";
            this.radNoCV.Size = new System.Drawing.Size(66, 21);
            this.radNoCV.TabIndex = 4;
            this.radNoCV.TabStop = true;
            this.radNoCV.Text = "No CV";
            this.radNoCV.UseVisualStyleBackColor = true;
            this.radNoCV.CheckedChanged += new System.EventHandler(this.radNoCV_CheckedChanged);
            // 
            // radCVDuck
            // 
            this.radCVDuck.AutoSize = true;
            this.radCVDuck.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radCVDuck.Location = new System.Drawing.Point(11, 35);
            this.radCVDuck.Name = "radCVDuck";
            this.radCVDuck.Size = new System.Drawing.Size(97, 21);
            this.radCVDuck.TabIndex = 5;
            this.radCVDuck.TabStop = true;
            this.radCVDuck.Text = "CV w/ Duck";
            this.radCVDuck.UseVisualStyleBackColor = true;
            this.radCVDuck.CheckedChanged += new System.EventHandler(this.radCVDuck_CheckedChanged);
            // 
            // radCVThing
            // 
            this.radCVThing.AutoSize = true;
            this.radCVThing.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radCVThing.Location = new System.Drawing.Point(11, 62);
            this.radCVThing.Name = "radCVThing";
            this.radCVThing.Size = new System.Drawing.Size(101, 21);
            this.radCVThing.TabIndex = 6;
            this.radCVThing.TabStop = true;
            this.radCVThing.Text = "CV w/ Thing";
            this.radCVThing.UseVisualStyleBackColor = true;
            this.radCVThing.CheckedChanged += new System.EventHandler(this.radCVThing_CheckedChanged);
            // 
            // grpAFreight
            // 
            this.grpAFreight.Controls.Add(this.radAFreightSU);
            this.grpAFreight.Controls.Add(this.radAFreightSH);
            this.grpAFreight.Location = new System.Drawing.Point(9, 178);
            this.grpAFreight.Name = "grpAFreight";
            this.grpAFreight.Size = new System.Drawing.Size(152, 78);
            this.grpAFreight.TabIndex = 11;
            this.grpAFreight.TabStop = false;
            this.grpAFreight.Text = "Preloaded freight in:";
            // 
            // radAFreightSU
            // 
            this.radAFreightSU.AutoSize = true;
            this.radAFreightSU.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radAFreightSU.Location = new System.Drawing.Point(11, 19);
            this.radAFreightSU.Name = "radAFreightSU";
            this.radAFreightSU.Size = new System.Drawing.Size(105, 21);
            this.radAFreightSU.TabIndex = 6;
            this.radAFreightSU.TabStop = true;
            this.radAFreightSU.Text = "Storage Unit";
            this.radAFreightSU.UseVisualStyleBackColor = true;
            this.radAFreightSU.CheckedChanged += new System.EventHandler(this.radAFreightSU_CheckedChanged);
            // 
            // radAFreightSH
            // 
            this.radAFreightSH.AutoSize = true;
            this.radAFreightSH.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radAFreightSH.Location = new System.Drawing.Point(11, 46);
            this.radAFreightSH.Name = "radAFreightSH";
            this.radAFreightSH.Size = new System.Drawing.Size(111, 21);
            this.radAFreightSH.TabIndex = 7;
            this.radAFreightSH.TabStop = true;
            this.radAFreightSH.Text = "Shipping Hub";
            this.radAFreightSH.UseVisualStyleBackColor = true;
            this.radAFreightSH.CheckedChanged += new System.EventHandler(this.radAFreightSH_CheckedChanged);
            // 
            // grpAParked
            // 
            this.grpAParked.Controls.Add(this.radAPartSU);
            this.grpAParked.Controls.Add(this.radACompSU);
            this.grpAParked.Controls.Add(this.radAPartWH);
            this.grpAParked.Controls.Add(this.radACompWH);
            this.grpAParked.Location = new System.Drawing.Point(9, 51);
            this.grpAParked.Name = "grpAParked";
            this.grpAParked.Size = new System.Drawing.Size(152, 131);
            this.grpAParked.TabIndex = 10;
            this.grpAParked.TabStop = false;
            this.grpAParked.Text = "Parked:";
            // 
            // radAPartSU
            // 
            this.radAPartSU.AutoSize = true;
            this.radAPartSU.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radAPartSU.Location = new System.Drawing.Point(10, 19);
            this.radAPartSU.Name = "radAPartSU";
            this.radAPartSU.Size = new System.Drawing.Size(100, 21);
            this.radAPartSU.TabIndex = 1;
            this.radAPartSU.TabStop = true;
            this.radAPartSU.Text = "Partly in SU";
            this.radAPartSU.UseVisualStyleBackColor = true;
            this.radAPartSU.CheckedChanged += new System.EventHandler(this.radAPartSU_CheckedChanged);
            // 
            // radACompSU
            // 
            this.radACompSU.AutoSize = true;
            this.radACompSU.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radACompSU.Location = new System.Drawing.Point(10, 46);
            this.radACompSU.Name = "radACompSU";
            this.radACompSU.Size = new System.Drawing.Size(100, 21);
            this.radACompSU.TabIndex = 2;
            this.radACompSU.TabStop = true;
            this.radACompSU.Text = "Comp in SU";
            this.radACompSU.UseVisualStyleBackColor = true;
            this.radACompSU.CheckedChanged += new System.EventHandler(this.radACompSU_CheckedChanged);
            // 
            // radAPartWH
            // 
            this.radAPartWH.AutoSize = true;
            this.radAPartWH.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radAPartWH.Location = new System.Drawing.Point(10, 73);
            this.radAPartWH.Name = "radAPartWH";
            this.radAPartWH.Size = new System.Drawing.Size(104, 21);
            this.radAPartWH.TabIndex = 3;
            this.radAPartWH.TabStop = true;
            this.radAPartWH.Text = "Partly in WH";
            this.radAPartWH.UseVisualStyleBackColor = true;
            this.radAPartWH.CheckedChanged += new System.EventHandler(this.radAPartWH_CheckedChanged);
            // 
            // radACompWH
            // 
            this.radACompWH.AutoSize = true;
            this.radACompWH.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radACompWH.Location = new System.Drawing.Point(10, 100);
            this.radACompWH.Name = "radACompWH";
            this.radACompWH.Size = new System.Drawing.Size(104, 21);
            this.radACompWH.TabIndex = 4;
            this.radACompWH.TabStop = true;
            this.radACompWH.Text = "Comp in WH";
            this.radACompWH.UseVisualStyleBackColor = true;
            this.radACompWH.CheckedChanged += new System.EventHandler(this.radACompWH_CheckedChanged);
            // 
            // chkADuck
            // 
            this.chkADuck.AutoSize = true;
            this.chkADuck.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chkADuck.Location = new System.Drawing.Point(15, 24);
            this.chkADuck.Name = "chkADuck";
            this.chkADuck.Size = new System.Drawing.Size(123, 21);
            this.chkADuck.TabIndex = 0;
            this.chkADuck.Text = "Duck Delivered";
            this.chkADuck.UseVisualStyleBackColor = true;
            this.chkADuck.CheckedChanged += new System.EventHandler(this.chkADuck_CheckedChanged);
            // 
            // grpTeleOp
            // 
            this.grpTeleOp.Controls.Add(this.lblTOSharedHub);
            this.grpTeleOp.Controls.Add(this.lblTOShipHub3);
            this.grpTeleOp.Controls.Add(this.lblTOShipHub2);
            this.grpTeleOp.Controls.Add(this.lblTOShipHub1);
            this.grpTeleOp.Controls.Add(this.lblTOStorageUnit);
            this.grpTeleOp.Controls.Add(this.numTOSharedHub);
            this.grpTeleOp.Controls.Add(this.numTOStorageUnit);
            this.grpTeleOp.Controls.Add(this.numTOShipHub3);
            this.grpTeleOp.Controls.Add(this.numTOShipHub2);
            this.grpTeleOp.Controls.Add(this.numTOShipHub1);
            this.grpTeleOp.Location = new System.Drawing.Point(198, 12);
            this.grpTeleOp.Name = "grpTeleOp";
            this.grpTeleOp.Size = new System.Drawing.Size(171, 348);
            this.grpTeleOp.TabIndex = 7;
            this.grpTeleOp.TabStop = false;
            this.grpTeleOp.Text = "TeleOp";
            // 
            // lblTOSharedHub
            // 
            this.lblTOSharedHub.AutoSize = true;
            this.lblTOSharedHub.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOSharedHub.Location = new System.Drawing.Point(17, 167);
            this.lblTOSharedHub.Name = "lblTOSharedHub";
            this.lblTOSharedHub.Size = new System.Drawing.Size(84, 17);
            this.lblTOSharedHub.TabIndex = 9;
            this.lblTOSharedHub.Text = "Shared Hub";
            // 
            // lblTOShipHub3
            // 
            this.lblTOShipHub3.AutoSize = true;
            this.lblTOShipHub3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOShipHub3.Location = new System.Drawing.Point(23, 125);
            this.lblTOShipHub3.Name = "lblTOShipHub3";
            this.lblTOShipHub3.Size = new System.Drawing.Size(78, 17);
            this.lblTOShipHub3.TabIndex = 8;
            this.lblTOShipHub3.Text = "Ship Hub 3";
            // 
            // lblTOShipHub2
            // 
            this.lblTOShipHub2.AutoSize = true;
            this.lblTOShipHub2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOShipHub2.Location = new System.Drawing.Point(23, 96);
            this.lblTOShipHub2.Name = "lblTOShipHub2";
            this.lblTOShipHub2.Size = new System.Drawing.Size(78, 17);
            this.lblTOShipHub2.TabIndex = 7;
            this.lblTOShipHub2.Text = "Ship Hub 2";
            // 
            // lblTOShipHub1
            // 
            this.lblTOShipHub1.AutoSize = true;
            this.lblTOShipHub1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOShipHub1.Location = new System.Drawing.Point(23, 67);
            this.lblTOShipHub1.Name = "lblTOShipHub1";
            this.lblTOShipHub1.Size = new System.Drawing.Size(78, 17);
            this.lblTOShipHub1.TabIndex = 6;
            this.lblTOShipHub1.Text = "Ship Hub 1";
            // 
            // lblTOStorageUnit
            // 
            this.lblTOStorageUnit.AutoSize = true;
            this.lblTOStorageUnit.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOStorageUnit.Location = new System.Drawing.Point(14, 24);
            this.lblTOStorageUnit.Name = "lblTOStorageUnit";
            this.lblTOStorageUnit.Size = new System.Drawing.Size(87, 17);
            this.lblTOStorageUnit.TabIndex = 5;
            this.lblTOStorageUnit.Text = "Storage Unit";
            // 
            // numTOSharedHub
            // 
            this.numTOSharedHub.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numTOSharedHub.Location = new System.Drawing.Point(107, 165);
            this.numTOSharedHub.Name = "numTOSharedHub";
            this.numTOSharedHub.Size = new System.Drawing.Size(40, 23);
            this.numTOSharedHub.TabIndex = 4;
            this.numTOSharedHub.ValueChanged += new System.EventHandler(this.numTOSharedHub_ValueChanged);
            // 
            // numTOStorageUnit
            // 
            this.numTOStorageUnit.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numTOStorageUnit.Location = new System.Drawing.Point(107, 22);
            this.numTOStorageUnit.Name = "numTOStorageUnit";
            this.numTOStorageUnit.Size = new System.Drawing.Size(40, 23);
            this.numTOStorageUnit.TabIndex = 3;
            this.numTOStorageUnit.ValueChanged += new System.EventHandler(this.numTOStorageUnit_ValueChanged);
            // 
            // numTOShipHub3
            // 
            this.numTOShipHub3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numTOShipHub3.Location = new System.Drawing.Point(107, 123);
            this.numTOShipHub3.Name = "numTOShipHub3";
            this.numTOShipHub3.Size = new System.Drawing.Size(40, 23);
            this.numTOShipHub3.TabIndex = 2;
            this.numTOShipHub3.ValueChanged += new System.EventHandler(this.numTOShipHub3_ValueChanged);
            // 
            // numTOShipHub2
            // 
            this.numTOShipHub2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numTOShipHub2.Location = new System.Drawing.Point(107, 94);
            this.numTOShipHub2.Name = "numTOShipHub2";
            this.numTOShipHub2.Size = new System.Drawing.Size(40, 23);
            this.numTOShipHub2.TabIndex = 1;
            this.numTOShipHub2.ValueChanged += new System.EventHandler(this.numTOShipHub2_ValueChanged);
            // 
            // numTOShipHub1
            // 
            this.numTOShipHub1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numTOShipHub1.Location = new System.Drawing.Point(107, 65);
            this.numTOShipHub1.Name = "numTOShipHub1";
            this.numTOShipHub1.Size = new System.Drawing.Size(40, 23);
            this.numTOShipHub1.TabIndex = 0;
            this.numTOShipHub1.ValueChanged += new System.EventHandler(this.numTOShipHub1_ValueChanged);
            // 
            // grpEndgame
            // 
            this.grpEndgame.Controls.Add(this.numEDucks);
            this.grpEndgame.Controls.Add(this.lblEDucks);
            this.grpEndgame.Controls.Add(this.groupBox1);
            this.grpEndgame.Controls.Add(this.chkESHCap);
            this.grpEndgame.Controls.Add(this.chkESHBal);
            this.grpEndgame.Controls.Add(this.chkEAHBal);
            this.grpEndgame.Location = new System.Drawing.Point(375, 12);
            this.grpEndgame.Name = "grpEndgame";
            this.grpEndgame.Size = new System.Drawing.Size(171, 348);
            this.grpEndgame.TabIndex = 8;
            this.grpEndgame.TabStop = false;
            this.grpEndgame.Text = "Endgame";
            // 
            // numEDucks
            // 
            this.numEDucks.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numEDucks.Location = new System.Drawing.Point(125, 19);
            this.numEDucks.Name = "numEDucks";
            this.numEDucks.Size = new System.Drawing.Size(40, 23);
            this.numEDucks.TabIndex = 10;
            this.numEDucks.ValueChanged += new System.EventHandler(this.numEDucks_ValueChanged);
            // 
            // lblEDucks
            // 
            this.lblEDucks.AutoSize = true;
            this.lblEDucks.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblEDucks.Location = new System.Drawing.Point(9, 22);
            this.lblEDucks.Name = "lblEDucks";
            this.lblEDucks.Size = new System.Drawing.Size(115, 17);
            this.lblEDucks.TabIndex = 17;
            this.lblEDucks.Text = "Ducks Delivered:";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.radEPWH);
            this.groupBox1.Controls.Add(this.radECWH);
            this.groupBox1.Location = new System.Drawing.Point(6, 132);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(159, 72);
            this.groupBox1.TabIndex = 11;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Parked:";
            // 
            // radEPWH
            // 
            this.radEPWH.AutoSize = true;
            this.radEPWH.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radEPWH.Location = new System.Drawing.Point(6, 19);
            this.radEPWH.Name = "radEPWH";
            this.radEPWH.Size = new System.Drawing.Size(104, 21);
            this.radEPWH.TabIndex = 3;
            this.radEPWH.TabStop = true;
            this.radEPWH.Text = "Partly in WH";
            this.radEPWH.UseVisualStyleBackColor = true;
            this.radEPWH.CheckedChanged += new System.EventHandler(this.radEPWH_CheckedChanged);
            // 
            // radECWH
            // 
            this.radECWH.AutoSize = true;
            this.radECWH.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radECWH.Location = new System.Drawing.Point(6, 46);
            this.radECWH.Name = "radECWH";
            this.radECWH.Size = new System.Drawing.Size(104, 21);
            this.radECWH.TabIndex = 4;
            this.radECWH.TabStop = true;
            this.radECWH.Text = "Comp in WH";
            this.radECWH.UseVisualStyleBackColor = true;
            this.radECWH.CheckedChanged += new System.EventHandler(this.radECWH_CheckedChanged);
            // 
            // chkESHCap
            // 
            this.chkESHCap.AutoSize = true;
            this.chkESHCap.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chkESHCap.Location = new System.Drawing.Point(15, 105);
            this.chkESHCap.Name = "chkESHCap";
            this.chkESHCap.Size = new System.Drawing.Size(138, 21);
            this.chkESHCap.TabIndex = 16;
            this.chkESHCap.Text = "Ship Hub Capped";
            this.chkESHCap.UseVisualStyleBackColor = true;
            this.chkESHCap.CheckedChanged += new System.EventHandler(this.chkESHCap_CheckedChanged);
            // 
            // chkESHBal
            // 
            this.chkESHBal.AutoSize = true;
            this.chkESHBal.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chkESHBal.Location = new System.Drawing.Point(15, 78);
            this.chkESHBal.Name = "chkESHBal";
            this.chkESHBal.Size = new System.Drawing.Size(127, 21);
            this.chkESHBal.TabIndex = 15;
            this.chkESHBal.Text = "Shared Hub Bal";
            this.chkESHBal.UseVisualStyleBackColor = true;
            this.chkESHBal.CheckedChanged += new System.EventHandler(this.chkESHBal_CheckedChanged);
            // 
            // chkEAHBal
            // 
            this.chkEAHBal.AutoSize = true;
            this.chkEAHBal.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.chkEAHBal.Location = new System.Drawing.Point(15, 51);
            this.chkEAHBal.Name = "chkEAHBal";
            this.chkEAHBal.Size = new System.Drawing.Size(130, 21);
            this.chkEAHBal.TabIndex = 14;
            this.chkEAHBal.Text = "Alliance Hub Bal";
            this.chkEAHBal.UseVisualStyleBackColor = true;
            this.chkEAHBal.CheckedChanged += new System.EventHandler(this.chkEAHBal_CheckedChanged);
            // 
            // lblAPoints
            // 
            this.lblAPoints.AutoSize = true;
            this.lblAPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblAPoints.Location = new System.Drawing.Point(100, 363);
            this.lblAPoints.Name = "lblAPoints";
            this.lblAPoints.Size = new System.Drawing.Size(16, 17);
            this.lblAPoints.TabIndex = 9;
            this.lblAPoints.Text = "0";
            this.lblAPoints.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblTOPoints
            // 
            this.lblTOPoints.AutoSize = true;
            this.lblTOPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTOPoints.Location = new System.Drawing.Point(277, 363);
            this.lblTOPoints.Name = "lblTOPoints";
            this.lblTOPoints.Size = new System.Drawing.Size(16, 17);
            this.lblTOPoints.TabIndex = 10;
            this.lblTOPoints.Text = "0";
            this.lblTOPoints.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblEPoints
            // 
            this.lblEPoints.AutoSize = true;
            this.lblEPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblEPoints.Location = new System.Drawing.Point(454, 363);
            this.lblEPoints.Name = "lblEPoints";
            this.lblEPoints.Size = new System.Drawing.Size(16, 17);
            this.lblEPoints.TabIndex = 11;
            this.lblEPoints.Text = "0";
            this.lblEPoints.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblPoints
            // 
            this.lblPoints.AutoSize = true;
            this.lblPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPoints.Location = new System.Drawing.Point(230, 391);
            this.lblPoints.Name = "lblPoints";
            this.lblPoints.Size = new System.Drawing.Size(87, 17);
            this.lblPoints.TabIndex = 12;
            this.lblPoints.Text = "Total Points:";
            // 
            // lblTotPoints
            // 
            this.lblTotPoints.AutoSize = true;
            this.lblTotPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTotPoints.Location = new System.Drawing.Point(323, 391);
            this.lblTotPoints.Name = "lblTotPoints";
            this.lblTotPoints.Size = new System.Drawing.Size(16, 17);
            this.lblTotPoints.TabIndex = 13;
            this.lblTotPoints.Text = "0";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(568, 422);
            this.Controls.Add(this.lblTotPoints);
            this.Controls.Add(this.lblPoints);
            this.Controls.Add(this.lblEPoints);
            this.Controls.Add(this.lblTOPoints);
            this.Controls.Add(this.lblAPoints);
            this.Controls.Add(this.grpEndgame);
            this.Controls.Add(this.grpTeleOp);
            this.Controls.Add(this.grpAuto);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "Freight Frenzy Points Calculator";
            this.grpAuto.ResumeLayout(false);
            this.grpAuto.PerformLayout();
            this.grpCV.ResumeLayout(false);
            this.grpCV.PerformLayout();
            this.grpAFreight.ResumeLayout(false);
            this.grpAFreight.PerformLayout();
            this.grpAParked.ResumeLayout(false);
            this.grpAParked.PerformLayout();
            this.grpTeleOp.ResumeLayout(false);
            this.grpTeleOp.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numTOSharedHub)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOStorageUnit)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numTOShipHub1)).EndInit();
            this.grpEndgame.ResumeLayout(false);
            this.grpEndgame.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numEDucks)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox grpAuto;
        private System.Windows.Forms.GroupBox grpTeleOp;
        private System.Windows.Forms.Label lblTOSharedHub;
        private System.Windows.Forms.Label lblTOShipHub3;
        private System.Windows.Forms.Label lblTOShipHub2;
        private System.Windows.Forms.Label lblTOShipHub1;
        private System.Windows.Forms.Label lblTOStorageUnit;
        private System.Windows.Forms.NumericUpDown numTOSharedHub;
        private System.Windows.Forms.NumericUpDown numTOStorageUnit;
        private System.Windows.Forms.NumericUpDown numTOShipHub3;
        private System.Windows.Forms.NumericUpDown numTOShipHub2;
        private System.Windows.Forms.NumericUpDown numTOShipHub1;
        private System.Windows.Forms.GroupBox grpEndgame;
        private System.Windows.Forms.RadioButton radACompSU;
        private System.Windows.Forms.RadioButton radAPartSU;
        private System.Windows.Forms.CheckBox chkADuck;
        private System.Windows.Forms.RadioButton radACompWH;
        private System.Windows.Forms.RadioButton radAPartWH;
        private System.Windows.Forms.RadioButton radAFreightSH;
        private System.Windows.Forms.RadioButton radAFreightSU;
        private System.Windows.Forms.GroupBox grpAParked;
        private System.Windows.Forms.GroupBox grpCV;
        private System.Windows.Forms.RadioButton radNoCV;
        private System.Windows.Forms.RadioButton radCVDuck;
        private System.Windows.Forms.RadioButton radCVThing;
        private System.Windows.Forms.GroupBox grpAFreight;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.RadioButton radEPWH;
        private System.Windows.Forms.RadioButton radECWH;
        private System.Windows.Forms.CheckBox chkESHCap;
        private System.Windows.Forms.CheckBox chkESHBal;
        private System.Windows.Forms.CheckBox chkEAHBal;
        private System.Windows.Forms.Label lblAPoints;
        private System.Windows.Forms.Label lblTOPoints;
        private System.Windows.Forms.Label lblEPoints;
        private System.Windows.Forms.Label lblPoints;
        private System.Windows.Forms.Label lblTotPoints;
        private System.Windows.Forms.NumericUpDown numEDucks;
        private System.Windows.Forms.Label lblEDucks;
    }
}

