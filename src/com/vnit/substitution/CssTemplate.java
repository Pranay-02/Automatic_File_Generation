package com.vnit.substitution;

public class CssTemplate {
    
    public String getCssString() {
        String temp = "";

        temp += ":host ::ng-deep .p-dropdown {\n" +
            "\twidth: 100%;\n" + 
            "\t/* border: 1px solid red; */\n" + 
        "}\n\n";
        
        temp += "fieldset.scheduler-border {\n" +
            "\tborder: 1px groove #ddd !important;\n" +
            "\tmargin: 0 0 1.5em 0 !important;\n" + 
            "\t-webkit-box-shadow:  0px 0px 0px 0px #000;\n" +
            "\tbox-shadow:  0px 0px 0px 0px #000;\n" +
        "}\n\n";
        
        temp += "legend.scheduler-border {\n" + 
            "\tfont-size: 1.2em !important;\n" +
            "\tfont-weight: bold !important;\n" +
            "\ttext-align: left !important;\n" +
            "\twidth:auto;\n" +
            "\tpadding:0 10px;\n" +
            "\tborder-bottom:none;\n" +
            "\tmargin-bottom: 0px !important;\n" +
        "}\n\n";
        
        temp += ":host ::ng-deep .prime-override-css{\n" +
            "\tborder: 1px solid red;\n" +
        "}\n\n";
        
        temp += ".cancel_button {\n" +
            "\tborder-radius: 22px;\n" +
            "\topacity: 1;\n" +
            "\twidth: 90px;\n" +
            "\theight: 30px;\n" +
          "}\n\n";
        
        temp += ":host ::ng-deep .tabview-custom i, :host ::ng-deep .tabview-custom span {\n" +
             "\tvertical-align: middle;\n" +
        "}\n\n";

        temp += ":host ::ng-deep .tabview-custom span {\n" + 
             "\tmargin: 0 0.5rem;\n" + 
        "}\n\n";

        temp += ":host ::ng-deep .p-button {\n" + 
             "\tmargin-right: 0.25rem;\n" + 
        "}\n\n";

        // temp += ":host ::ng-deep .p-tabview p {
        //      line-height: 1.5;
        //      margin: 0;
        // }
        
        
        // :host ::ng-deep .p-datepicker table td > span {
        //     width: 1.1rem;
        //     height: 1.1rem;
        //     border-radius: 50%;
        //     transition: box-shadow 0.2s;
        //     border: 1px solid transparent;
        // }
        
        
        
        
        
        // :host ::ng-deep .p-datatable .p-datatable-tbody > tr > td {
        //     padding: 3px !important;
        // }
        
        // :host ::ng-deep .p-datatable .p-datatable-thead > tr > th {
        //         padding: 3px !important;
        // }
        
        // /* ..... */
        
        
        // :host ::ng-deep .p-datatable-responsive-demo .p-datatable-tbody > tr > td .p-column-title {
        //     display: none;
        // }
        // @media screen and (max-width: 40rem) {
        //     :host ::ng-deep .p-datatable.p-datatable-responsive-demo .p-datatable-thead > tr > th, :host ::ng-deep .p-datatable.p-datatable-responsive-demo .p-datatable-tfoot > tr > td {
        //         display: none !important;
        //    }
        //     :host ::ng-deep .p-datatable.p-datatable-responsive-demo .p-datatable-tbody > tr > td {
        //         text-align: left;
        //         display: block;
        //         width: 100%;
        //         float: left;
        //         clear: left;
        //         border: 0 none;
        //    }
        //     :host ::ng-deep .p-datatable.p-datatable-responsive-demo .p-datatable-tbody > tr > td .p-column-title {
        //         padding: 0.4rem;
        //         min-width: 30%;
        //         display: inline-block;
        //         margin: -0.4em 1em -0.4em -0.4rem;
        //         font-weight: bold;
        //    }
        //     :host ::ng-deep .p-datatable.p-datatable-responsive-demo .p-datatable-tbody > tr > td:last-child {
        //         border-bottom: 1px solid var(--surface-d);
        //    }
        // }
        
        // /* ..... */
        
        // /* remove <> from input type number */
        // input::-webkit-outer-spin-button,
        // input::-webkit-inner-spin-button {
        //   -webkit-appearance: none;
        //   margin: 0;
        // }
        
        // /* Firefox */
        // input[type=number] {
        //   -moz-appearance: textfield;
        // }
        // /* remove <> from input type number */
        
        // /*Image Lov Dropdown css */
        //    :host ::ng-deep .p-dropdown {
        //     width: 100%;
        //   }
          
        //    /*Image Lov Dropdown Image icon css  */
        //   :host ::ng-deep .item-img-wrap {
        //           display: flex;
        //           align-items: center;
        //       }
            
        //   :host ::ng-deep  .item-img-wrap img.img {
        //       width: 18px;
        //       height: 18px;
        //       margin-right: .5rem;
        //   }
        
        
        //   /* Report Modal css   */
        
        // :host ::ng-deep .p-dialog .p-dialog-content {
        //     padding: 0px;
        // }
        // .save_button {
        //     border: 1px solid #4CA07A;
        //     border-radius: 22px;
        //     opacity: 1;
        //     font-size: 13px;
        //     background-color: #4CA07A;
        //     width: 90px;
        //     height: 30px;
        // }
        
        
        // :host ::ng-deep .p-datatable .p-datatable-header {
        //     padding: 0px !important;
        // }"

        return temp;
    }
}
