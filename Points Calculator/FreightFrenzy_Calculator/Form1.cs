using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace FreightFrenzy_Calculator
{
    public partial class Form1 : Form
    {
        int AutoPoints;
        int TeleOpPoints;
        int EndgamePoints;
        
        public Form1()
        {
            InitializeComponent();
        }

        private void UpdateTotals()
        {
            AutoPoints = 0;
            TeleOpPoints = 0;
            EndgamePoints = 0;
            if (chkADuck.Checked)
            {
                AutoPoints += 10;
            }
            if (radAPartSU.Checked)
            {
                AutoPoints += 3;
            }
            if (radACompSU.Checked)
            {
                AutoPoints += 6;
            }
            if (radAPartWH.Checked)
            {
                AutoPoints += 5;
            }
            if (radACompWH.Checked)
            {
                AutoPoints += 10;
            }
            if (radAFreightSU.Checked)
            {
                AutoPoints += 2;
            }
            if (radAFreightSH.Checked)
            {
                AutoPoints += 6;
                if (radCVDuck.Checked)
                {
                    AutoPoints += 10;
                }
                if (radCVThing.Checked)
                {
                    AutoPoints += 20;
                }
            }
            lblAPoints.Text = Convert.ToString(AutoPoints);
            TeleOpPoints += (int)numTOStorageUnit.Value;
            TeleOpPoints += (int)numTOShipHub1.Value * 2;
            TeleOpPoints += (int)numTOShipHub2.Value * 4;
            TeleOpPoints += (int)numTOShipHub3.Value * 6;
            TeleOpPoints += (int)numTOSharedHub.Value * 4;
            lblTOPoints.Text = Convert.ToString(TeleOpPoints);
            EndgamePoints += 6 * (int)numEDucks.Value;
            if (chkEAHBal.Checked)
            {
                EndgamePoints += 10;
            }
            if (chkESHBal.Checked)
            {
                EndgamePoints += 20;
            }
            if (chkESHCap.Checked)
            {
                EndgamePoints += 15;
            }
            if (radEPWH.Checked)
            {
                EndgamePoints += 3;
            }
            if (radECWH.Checked)
            {
                EndgamePoints += 6;
            }
            lblEPoints.Text = Convert.ToString(EndgamePoints);
            lblTotPoints.Text = Convert.ToString(AutoPoints + TeleOpPoints + EndgamePoints);
        }

        private void radAFreightSU_CheckedChanged(object sender, EventArgs e)
        {
            grpCV.Visible = false;
            UpdateTotals();
        }
        private void radAFreightSH_CheckedChanged(object sender, EventArgs e)
        {
            grpCV.Visible = true;
            UpdateTotals();
        }

        private void chkADuck_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radAPartSU_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radACompSU_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radAPartWH_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radACompWH_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radNoCV_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radCVDuck_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radCVThing_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numTOStorageUnit_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numTOShipHub1_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numTOShipHub2_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numTOShipHub3_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numTOSharedHub_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void numEDucks_ValueChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void chkEAHBal_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void chkESHBal_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void chkESHCap_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radEPWH_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
        private void radECWH_CheckedChanged(object sender, EventArgs e)
        {
            UpdateTotals();
        }
    }
}
