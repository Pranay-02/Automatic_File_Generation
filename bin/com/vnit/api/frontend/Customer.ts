import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Component({
	selector: 'app-customer-master',
	templateUrl: './customer-master.component.html',
	styleUrls: ['./customer-master.component.css']
})

export class CustomerMasterComponent implements OnInit {
	@ViewChild('f', { static: false }) form: NgForm;
	model: any = {}
	modelOneArray: any = [];
	modelList = []
	searchFromFilter: boolean = false;
	filters = ""

	constructor(private configService : ConfigService,private notificationServices: NotificationServices,private crudService: CrudService,) { }
	ngOnInit(): void {
		this.onRefresh()
	}

	onRefresh(){
		this.model = {
			 "ccode": null,
			 "cname": "",
		}
		this.modelOneArray = []

		this.modelList = []

		this.getModelList("")

	}

	getModelList(name){
		this.modelList = []
		this.crudService.commonActionPerformGet(credentials.INVENTORY + 'get_customer_list' + `${"?"+'name='}`+name).subscribe(response => {
			this.modelList = response.data;
		}, (error) => {
			console.log("getRewsRoomListError=", JSON.stringify(error));
		});
	}
	searchByFilter(){
		this.getModelList(this.filters)
	}

	clearModelOne() {
		this.model = {
			"ccode": null,
			"cname": "",
		}
		document.getElementById("cname").focus();
	}
	addModelOneArray() {
		if (this.configService.isNullUndefined(this.model.cname) === false) {
			this.notificationServices.showNotification('error', "Customer Name Required");
			document.getElementById("cname").focus();
			return false;
		}
		var json: any = {} = Object.assign({}, this.model);
		if (json.index || json.index === 0) {
			this.modelOneArray[json.index] = json
			console.log("old")
		}
		else {
			json.index = this.modelOneArray.length;
			this.modelOneArray[json.index] = json
			console.log("new")
		}
		this.clearModelOne()
	}

	async deleteRowData(data, index) {
		await this.modelOneArray.splice(index, 1)
		this.modelOneArray.forEach((element, index) => {
			element["index"] = index;
		});
	}

	viewRowData(datas, index) {
		var tempData: any = {};
		tempData = Object.assign({}, datas);
		this.model = tempData
	}

	onCancel(){
		if(this.searchFromFilter === false){
			this.searchFromFilter = true;
		}
		else{
			this.searchFromFilter = false
		}
		this.onRefresh()
	}

	async onSave() {
		this.configService.enabledLoader();
		if (this.modelOneArray.length === 0) {
			this.notificationServices.showNotification('error', "One sample detail must be added");
			this.configService.disableLoader();
			return;
		}

		var postJson: any = [];
		// postJson = Object.assign({}, this.model);
		postJson = [... this.modelOneArray];

		for await (const [index, element] of postJson.entries()) {
			await this.saveElement(element)
		}
		this.onRefresh()
		this.notificationServices.showNotification('success', "Save Successfully");
		this.configService.disableLoader();
	}

	async saveElement(element) {
		return new Promise(resolve => {
			this.crudService.commonActionPerformPost(credentials.INVENTORY + 'post_customer', element).subscribe(async (response) => {
				if (response.status === await "Success") {
					return resolve(response);
				}
				else {
					this.notificationServices.showNotification('error', response.message);
					this.configService.disableLoader();
					return resolve(response);
				}
			}, (error) => {
				console.log("getModelListError=", JSON.stringify(error))
				this.notificationServices.showNotification('error', error);
				this.configService.disableLoader();
			});
		})
	}

	onDelete(modelOneArray,i){
		this.model = modelOneArray
	}

	confirmDelete(){
		this.configService.enabledLoader()
		this.crudService.commonActionPerformDelete(credentials.INVENTORY + 'delete_customer/'+ this.model.ccode).subscribe(async (response) => {
			if (response.status === await "Success") {
				this.notificationServices.showNotification('error', response.message);
				this.onRefresh()
				this.configService.disableLoader()
			}
			else {
				this.notificationServices.showNotification('error', response.message);
				this.onRefresh()
				this.configService.disableLoader();
			}
		}, (error) => {
			console.log("getModelListError=", JSON.stringify(error))
			this.notificationServices.showNotification('error', error);
			this.configService.disableLoader();
		});
	}

}
