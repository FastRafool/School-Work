import os
import boto3
from aws_lambda_powertools.utilities.data_classes import APIGatewayProxyEvent
from aws_lambda_powertools.event_handler.api_gateway import ApiGatewayResolver
from aws_lambda_powertools import Logger, Tracer
from pydantic import BaseModel, ValidationError

logger = Logger()
tracer = Tracer()

app = ApiGatewayResolver()

dynamodb = boto3.resource("dynamodb")
table = dynamodb.Table(os.environ.get("SUBSCRIBERS_TABLE_NAME"))

class SubscriptionRequest(BaseModel):
    email: str
    frequency: str
    keywords: list

@app.post("/subs")
@tracer.capture_method
def subscribe():
    event = APIGatewayProxyEvent(app.current_event)
    try:
        request_data = SubscriptionRequest(**event.json_body)
        response = table.put_item(Item={
            "email": request_data.email,
            "frequency": request_data.frequency,
            "keywords": request_data.keywords,
        })
        logger.info("Subscription added successfully.")
        return {"statusCode": 200, "body": "Subscription successful"}
    except ValidationError as e:
        logger.error(f"Validation error: {str(e)}")
        return {"statusCode": 400, "body": str(e)}
    except Exception as e:
        logger.error(f"Error processing subscription: {str(e)}")
        return {"statusCode": 500, "body": "Failed to add subscription"}

def lambda_handler(event: dict, context):
    return app.resolve(event, context)
